package com.example.kb_jay.kj_test;

import android.graphics.Color;

import java.util.Random;

public final class RandomColorBalance
{
    static
    {
        new Random();
    }

    public static int getActionColor(int paramInt1, int paramInt2)
    {
        int i2 = paramInt1 >> 24 & 0xFF;
        int i5 = paramInt2 >> 16 & 0xFF;
        int i4 = paramInt2 >> 8 & 0xFF;
        int i3 = paramInt2 & 0xFF;
        int i1 = Math.min(paramInt1 >> 16 & 0xFF, i5);
        int m = Math.min(paramInt1 >> 8 & 0xFF, i4);
        int n = Math.min(paramInt1 & 0xFF, i3);
        int k = i2;
        int j = m;
        int i = n;
        paramInt1 = i1;
        if (i2 != 255)
        {
            k = i2 * 255 / 255;
            paramInt2 = (255 - k) * (paramInt2 >> 24 & 0xFF) / 255;
            paramInt1 = getActionMask((i1 * k + i5 * paramInt2) / 255);
            j = getActionMask((m * k + i4 * paramInt2) / 255);
            i = getActionMask((n * k + i3 * paramInt2) / 255);
            k = getActionMask(k + paramInt2);
        }
        return paramInt1 << 16 | k << 24 | j << 8 | i;
    }

    public static int getActionMask(int paramInt)
    {
        if (paramInt < 0) {
            return 0;
        }
        if (paramInt > 255) {
            return 255;
        }
        return paramInt;
    }

    public static void getColorRGB(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2)
    {
        int i = 0;
        while (i < paramInt1)
        {
            int j = 0;
            while (j < paramInt2)
            {
                int k = j * paramInt1 + i;
                int i2 = paramArrayOfInt1[k];
                int i1 = paramArrayOfInt2[k];
                int m = Color.red(i2);
                int n = Color.green(i2);
                i2 = Color.blue(i2);
                int i3 = Color.red(i1);
                int i4 = Color.green(i1);
                i1 = Color.blue(i1);
                paramArrayOfInt2[k] = Color.argb(255, getRandColorInt(m, i3), getRandColorInt(n, i4), getRandColorInt(i2, i1));
                j += 1;
            }
            i += 1;
        }
    }

    private static int getRandColorInt(int paramInt1, int paramInt2)
    {
        paramInt1 = paramInt1 * paramInt2 / (256 - paramInt2) + paramInt1;
        if (paramInt1 > 255) {
            return 255;
        }
        return paramInt1;
    }
}
