package de.epprojekt.ep_sjpp_prototyp;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;


public class AnimationsHelferlein {

    public void ownAnimation( ImageButton item, int startX, int startY, int destinationX, int destinationY){

        //gewähltes icon kleiner machen und nach rechts bewegen
        AnimationSet scaleAndMoveToRightAnimationSet = new AnimationSet(false);

        ScaleAnimation scaleToRight = new ScaleAnimation(1,(float)0.8,1,(float)0.8);
        scaleToRight.setDuration(200);
        scaleAndMoveToRightAnimationSet.addAnimation(scaleToRight);

        TranslateAnimation translateToRight = new TranslateAnimation(startX,destinationX,startY,startY);
        translateToRight.setDuration(200);
        scaleAndMoveToRightAnimationSet.addAnimation(translateToRight);

        //gewähltes icon nach oben auf den warenkorb bewegen
        AnimationSet moveupAnimationSet = new AnimationSet(false);

        TranslateAnimation translateUpwards = new TranslateAnimation(destinationX,destinationX,startY,destinationY);
        translateUpwards.setDuration(200);
        moveupAnimationSet.addAnimation(translateUpwards);

        item.startAnimation(scaleAndMoveToRightAnimationSet);
        item.startAnimation(scaleToRight);

        scaleAndMoveToRightAnimationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                item.setVisibility(ImageButton.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        moveupAnimationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                item.setVisibility(ImageButton.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}



