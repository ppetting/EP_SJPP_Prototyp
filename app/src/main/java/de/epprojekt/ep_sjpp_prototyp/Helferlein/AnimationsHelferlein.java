package de.epprojekt.ep_sjpp_prototyp.Helferlein;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;

public class AnimationsHelferlein {

    public void ownAnimation(ImageButton item, ImageButton warenkorb){

        //ANIMATIONSSET
        AnimationSet animationSet = new AnimationSet(false);

        //KLEINER WERDEN
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, (float) 0.15, 1, (float) 0.15);
        scaleAnimation.setDuration(400);
        animationSet.addAnimation(scaleAnimation);

        //NACH RECHTS FAHREN
        TranslateAnimation translateToRightAnimation = new TranslateAnimation(0, warenkorb.getX()-item.getX(), 0, 0);
        translateToRightAnimation.setDuration(400);
        translateToRightAnimation.setStartOffset(400);
        animationSet.addAnimation(translateToRightAnimation);

        //NACH OBEN FAHREN
        TranslateAnimation translateToTopAnimation = new TranslateAnimation(0, 0,0,warenkorb.getY()-item.getY()-50);
        translateToTopAnimation.setDuration(400);
        translateToTopAnimation.setStartOffset(800);
        animationSet.addAnimation(translateToTopAnimation);

        //ANIMATIONSSET CALL
        item.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
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

    }

    public void ownAnimationWithInvisible(ImageButton item, ImageButton warenkorb){

        //ANIMATIONSSET
        AnimationSet animationSet2 = new AnimationSet(false);

        //KLEINER WERDEN
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, (float) 0.15, 1, (float) 0.15);
        scaleAnimation.setDuration(400);
        animationSet2.addAnimation(scaleAnimation);

        //NACH RECHTS FAHREN
        TranslateAnimation translateToRightAnimation = new TranslateAnimation(0, warenkorb.getX()-item.getX(), 0, 0);
        translateToRightAnimation.setDuration(400);
        translateToRightAnimation.setStartOffset(400);
        animationSet2.addAnimation(translateToRightAnimation);

        //NACH OBEN FAHREN
        TranslateAnimation translateToTopAnimation = new TranslateAnimation(0, 0,0,warenkorb.getY()-item.getY()-50);
        translateToTopAnimation.setDuration(400);
        translateToTopAnimation.setStartOffset(800);
        animationSet2.addAnimation(translateToTopAnimation);

        //ANIMATIONSSET CALL
        item.startAnimation(animationSet2);

        animationSet2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                item.setVisibility(ImageButton.VISIBLE);

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



