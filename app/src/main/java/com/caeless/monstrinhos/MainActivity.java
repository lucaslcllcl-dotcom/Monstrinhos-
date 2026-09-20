package com.caeless.monstrinhos;
import android.app.Activity;import android.os.Bundle;import android.view.Window;import android.view.WindowManager;
public final class MainActivity extends Activity{
 private MonstrinhosView gameView;private MonetizationManager monetization;
 @Override public void onCreate(Bundle b){super.onCreate(b);requestWindowFeature(Window.FEATURE_NO_TITLE);getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);gameView=new MonstrinhosView(this);if(b!=null)gameView.restoreSession(b);setContentView(gameView);monetization=new MonetizationManager(this,new MonetizationManager.Listener(){public void onCatalogChanged(){if(gameView!=null)gameView.onMonetizationChanged();}public void onPurchaseGranted(String id,int coins,boolean removeAds){if(gameView!=null)gameView.onPurchaseGranted(id,coins,removeAds);}public void onRewardedAdEarned(int coins){if(gameView!=null)gameView.onRewardedAdEarned(coins);}public void onMessage(String m){if(gameView!=null)gameView.showMonetizationMessage(m);}});monetization.start();}
 String storePrice(String id){return monetization==null?"Google Play":monetization.priceFor(id);}void buyProduct(String id){if(monetization!=null)monetization.launchPurchase(id);}void showRewardedCoins(){if(monetization!=null)monetization.showRewarded();}boolean showInterstitialThen(Runnable r){return monetization!=null&&monetization.showInterstitial(r);}
 @Override protected void onResume(){super.onResume();AchievementSystem.evaluate(this);if(gameView!=null)gameView.post(()->AchievementCelebrationController.showNext(MainActivity.this,gameView));}
 @Override protected void onSaveInstanceState(Bundle out){if(gameView!=null)gameView.saveSession(out);super.onSaveInstanceState(out);}
 @Override protected void onPause(){if(gameView!=null)gameView.persistProgress();AchievementSystem.evaluate(this);super.onPause();}
 @Override protected void onDestroy(){if(monetization!=null)monetization.close();super.onDestroy();}
}
