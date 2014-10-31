package challenge.ckl;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import com.etsy.android.grid.util.DynamicHeightTextView;

/**
 * Created by lucoceano
 * on 14/06/2013.
 */
public class TextViewPlus extends DynamicHeightTextView {
    
    private static final String TAG = "TextView";

    public TextViewPlus(Context context) {
        super(context);
    }

    public TextViewPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.TextViewPlus);
        assert a != null;
        String customFont = a.getString(R.styleable.TextViewPlus_fontName);
        setCustomFont(ctx, customFont);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        Typeface tf = null;
        try {
            tf = Typeface.createFromAsset(ctx.getAssets(), asset);
        } catch (Exception e) {
            Log.e(TAG, "Could not get typeface[" + asset + "]: " + e.getMessage());
            return false;
        }

        setTypeface(tf);
        return true;
    }

}
