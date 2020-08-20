package com.qwang.blog.util;

/**
 * @author qwang
 * @date 2020/7/4
 */
public class CoverUtils {

    private static final String suffix = ".jpg";
    private static final String prefix = "/images/cover/";
    private static final int length = 10; // /images/avatar下的图片数量

    /**
     * 从/images/avatar下随机取图片作为头像
     * @return
     */
    public static String getRandomCover() {
        return prefix + (int) (Math.random() * length + 1) + suffix;
    }

}
