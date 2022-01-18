package de.epprojekt.ep_sjpp_prototyp;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;


public class AnimationsHelferlein {

    public void ownAnimation( ImageButton item, int startX, int startY, int destinationX, int destinationY){

        AnimationSet animationToLeftSide = new AnimationSet(true);

        ScaleAnimation scaleAnimationToLeft = new ScaleAnimation(1,(float)0.8,1,(float)0.8);
        scaleAnimationToLeft.setInterpolator(new BounceInterpolator());
        scaleAnimationToLeft.setDuration(200);
        scaleAnimationToLeft.setFillAfter(true);
        animationToLeftSide.addAnimation(scaleAnimationToLeft);

        TranslateAnimation translateToLeftAnimation = new TranslateAnimation(startX, destinationX, startY, startY);
        translateToLeftAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateToLeftAnimation.setDuration(200);
        animationToLeftSide.addAnimation(translateToLeftAnimation);

        AlphaAnimation alpha = new AlphaAnimation(1, (float) 0.8);
        alpha.setDuration(200);
        animationToLeftSide.addAnimation(alpha);

        final AnimationSet animationToBottom = new AnimationSet(true);
        animationToBottom.setInterpolator(new BounceInterpolator());

        ScaleAnimation scaleToBottomAnimation = new ScaleAnimation((float) 0.8, (float) 0.1, (float) 0.8, (float) 0.1);
        scaleToBottomAnimation.setDuration(400);
        scaleToBottomAnimation.setFillAfter(true);
        animationToBottom.addAnimation(scaleToBottomAnimation);

        TranslateAnimation translateToBottomAnimation = new TranslateAnimation(destinationX, destinationX, startY, destinationY);
        translateToBottomAnimation.setDuration(800);
        animationToBottom.addAnimation(translateToBottomAnimation);

        AlphaAnimation alphaToBottom = new AlphaAnimation((float) 0.8, (float) 0.6);
        alphaToBottom.setDuration(800);
        animationToBottom.addAnimation(alphaToBottom);

        animationToLeftSide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                item.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animationToBottom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                item.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

    });

    }
}



