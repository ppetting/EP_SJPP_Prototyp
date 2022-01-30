package de.epprojekt.ep_sjpp_prototyp;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;

public class AnimationsHelferlein {

    public void ownAnimation(ImageButton item, ImageButton warenkorb){

        //ANIMATIONSET
        AnimationSet animationSet = new AnimationSet(false);

        //KLEINER WERDEN
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, (float) 0.15, 1, (float) 0.15);
        scaleAnimation.setDuration(800);
        animationSet.addAnimation(scaleAnimation);

        //NACH RECHTS FAHREN
        TranslateAnimation translateToRightAnimation = new TranslateAnimation(0, warenkorb.getX()-item.getX(), 0, 0);
        translateToRightAnimation.setDuration(800);
        translateToRightAnimation.setStartOffset(800);
        animationSet.addAnimation(translateToRightAnimation);

        //NACH OBEN FAHREN
        TranslateAnimation translateToTopAnimation = new TranslateAnimation(0, 0,0,warenkorb.getY()-item.getY());
        translateToTopAnimation.setDuration(800);
        translateToTopAnimation.setStartOffset(1600);
        animationSet.addAnimation(translateToTopAnimation);

        //ANIMATIONSET CALL
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
}


