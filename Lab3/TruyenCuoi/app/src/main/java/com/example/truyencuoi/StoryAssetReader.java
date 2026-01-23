package com.example.truyencuoi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class StoryAssetReader {

    public static ArrayList<String> getAllTopics(Context context) {
        try {
            String[] files = context.getAssets().list("story");
            if (files == null) return new ArrayList<>();
            Arrays.sort(files);

            ArrayList<String> topics = new ArrayList<>();
            for (String f : files) {
                if (f.toLowerCase().endsWith(".txt")) {
                    topics.add(f.substring(0, f.length() - 4)); // bỏ .txt
                }
            }
            return topics;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static Bitmap loadTopicImage(Context context, String topic) {
        // topic.png nằm trong assets/photo/
        String file = "photo/" + topic + ".png";
        try (InputStream is = context.getAssets().open(file)) {
            return BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<StoryEntity> readStoriesOfTopic(Context context, String topic) {
        String file = "story/" + topic + ".txt";
        ArrayList<StoryEntity> list = new ArrayList<>();

        try (InputStream is = context.getAssets().open(file);
             BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {

            String curTitle = null;
            StringBuilder curContent = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                line = line.replace("\uFEFF", "");
                String t = line.trim();

                // bỏ dòng rác phân cách
                if (t.equals("','0');") || t.equals(",'0');") || t.equals("('0');")) {
                    continue;
                }

                // bỏ thừa dòng rỗng (nhưng giữ khoảng cách trong content)
                if (t.isEmpty()) {
                    if (curTitle != null && curContent.length() > 0) curContent.append("\n");
                    continue;
                }

                boolean looksLikeTitle =
                        !t.startsWith("-") &&
                                t.length() <= 60 &&
                                !t.contains(":") &&
                                !t.endsWith(".") &&
                                Character.isLetterOrDigit(t.charAt(0));

                if (looksLikeTitle) {
                    // gặp title mới -> chốt truyện cũ
                    if (curTitle != null) {
                        list.add(new StoryEntity(topic, curTitle, curContent.toString().trim()));
                    }
                    curTitle = t;
                    curContent.setLength(0);
                } else {
                    // content
                    if (curTitle == null) curTitle = "Không tiêu đề";
                    if (curContent.length() > 0) curContent.append("\n");
                    curContent.append(line);
                }
            }

            // chốt truyện cuối
            if (curTitle != null) {
                list.add(new StoryEntity(topic, curTitle, curContent.toString().trim()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
