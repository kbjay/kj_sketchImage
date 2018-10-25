package com.example.kb_jay.kj_test;

import android.graphics.Bitmap;
import android.graphics.Color;

public final class SecondSketchFilter
        extends ImageFilerName
{
    int value = 2;

    public static void simpleRGB(int[] paramArrayOfInt, int paramInt1, int paramInt2)
    {
        int i = 0;
        while (i < paramInt2)
        {
            int j = 0;
            while (j < paramInt1)
            {
                int k = i * paramInt1 + j;
                int m = Color.red(paramArrayOfInt[k]) * 28 + Color.green(paramArrayOfInt[k]) * 151 + Color.blue(paramArrayOfInt[k]) * 77 >> 8;
                paramArrayOfInt[k] = Color.rgb(m, m, m);
                j += 1;
            }
            i += 1;
        }
    }

    public final Bitmap getSimpleSketch(Bitmap paramBitmap)
    {
        System.currentTimeMillis();
        int i3 = paramBitmap.getWidth();
        int i4 = paramBitmap.getHeight();
        int k = i3 * i4;
        int[] arrayOfInt2 = new int[k];
        paramBitmap.getPixels(arrayOfInt2, 0, i3, 0, 0, i3, i4);
        int i = 0;
        int j;
        int m;
        int n;
        while (i < i4)
        {
            j = 0;
            while (j < i3)
            {
                m = i * i3 + j;
                n = 255 - (Color.red(arrayOfInt2[m]) * 28 + Color.green(arrayOfInt2[m]) * 151 + Color.blue(arrayOfInt2[m]) * 77 >> 8);
                arrayOfInt2[m] = Color.rgb(n, n, n);
                j += 1;
            }
            i += 1;
        }
        int[] arrayOfInt1 = new int[k];
        MinBlurValue localMinBlurValue = new MinBlurValue();
        localMinBlurValue.minBlurVal = this.value;
        System.currentTimeMillis();
        k = 0;
        i = k;
        while (k < i4)
        {
            m = 0;
            while (m < i3)
            {
                n = -1;
                int i1;
                for (j = -1; n <= 1; j = i1)
                {
                    int i5 = k + n;
                    i1 = j;
                    if (i5 >= 0)
                    {
                        i1 = j;
                        if (i5 < i4)
                        {
                            i1 = -localMinBlurValue.minBlurVal;
                            for (;;)
                            {
                                int i2 = localMinBlurValue.minBlurVal;
                                if (i1 > 0)
                                {
                                    i1 = j;
                                    break;
                                }
                                int i6 = m + i1;
                                i2 = j;
                                if (i6 >= 0)
                                {
                                    i2 = j;
                                    if (i6 < i3) {
                                        i2 = RandomColorBalance.getActionColor(j, arrayOfInt2[(i6 + i5 * i3)]);
                                    }
                                }
                                i1 += 1;
                                j = i2;
                            }
                        }
                    }
                    n += 1;
                }
                arrayOfInt1[i] = j;
                m += 1;
                i += 1;
            }
            k += 1;
        }
        paramBitmap.getPixels(arrayOfInt2, 0, i3, 0, 0, i3, i4);
        simpleRGB(arrayOfInt2, i3, i4);
        RandomColorBalance.getColorRGB(arrayOfInt2, arrayOfInt1, i3, i4);
        paramBitmap = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
        paramBitmap.setPixels(arrayOfInt1, 0, i3, 0, 0, i3, i4);
        System.gc();
        return paramBitmap;
    }

    public final void getSimpleSketchValue(int paramInt)
    {
        this.value = paramInt;
    }
}

