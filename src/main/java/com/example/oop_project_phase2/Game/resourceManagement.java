package com.example.oop_project_phase2.Game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.beans.EventHandler;
import java.io.File;
import java.net.MalformedURLException;


public class resourceManagement {
    public static Image fighter;
    public static Image wizard;
    public static Image dancer;
    public static Image gunner;
    public static Image rapidFire,shockimpairment,slideswiper,piercerbullet,hiddenshot,thermalsuppresion,earlypercision,acidcleanser;
    public static Image hallstorm,advancedfusion,bulletflurry,MBsilencer,Dissolver,UnshieldableGamma,SideWinder,IONBurst,SniperSupport,SonarStrike;
    public static Image toxicrecoil,HolyHeal,Mover,Scaffholding,Blackhole,TimeGod,Poisonpouch,Duplicator,SmokeBomb,StrengthPosion,shield,multifire;
    public static Image cell;
    public static double musicvolume = 0.5;
    public static Media RangoMusic;
    public static MediaPlayer MusicPlayer;
    public static void load() throws MalformedURLException {
        RangoMusic = new Media(new File("src/main/resources/com/example/oop_project_phase2/Y2meta.app - Walk Don't Rango (128 kbps).mp3").toURI().toURL().toString());
        MusicPlayer = new MediaPlayer(RangoMusic);
        MusicPlayer.setVolume(musicvolume);
        MusicPlayer.setCycleCount(Integer.MAX_VALUE);
        MusicPlayer.play();
    }
    static {
        try {
            cell=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/cell.png").toURI().toURL().toString());
            fighter=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/fighter.png").toURI().toURL().toString());
            gunner=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/gunner.jpg").toURI().toURL().toString());
            dancer=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/dancer.png").toURI().toURL().toString());
            wizard=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/wizard.png").toURI().toURL().toString());
            rapidFire=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/rapifFire.jpg").toURI().toURL().toString());
            shockimpairment=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/shockImpairment.jpg").toURI().toURL().toString());
            slideswiper=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/slideSwiper.jpg").toURI().toURL().toString());
            piercerbullet=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/piercerBullet.jpg").toURI().toURL().toString());
            hiddenshot=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/hiidenShot.jpg").toURI().toURL().toString());
            thermalsuppresion=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/thermalSuppresion.jpg").toURI().toURL().toString());
            earlypercision=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/earlyPercision.jpg").toURI().toURL().toString());
            acidcleanser=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/acid_cleanser.jpg").toURI().toURL().toString());
           hallstorm=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/hallStorm.png").toURI().toURL().toString());
            advancedfusion=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/advanced_fusion.jpg").toURI().toURL().toString());
            bulletflurry=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/bullet_fudion.jpg").toURI().toURL().toString());
            MBsilencer=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/Mb_silencer.jpg").toURI().toURL().toString());
            Dissolver=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/dissolver.jpg").toURI().toURL().toString());
            UnshieldableGamma=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/unshieldable_gama.jpg").toURI().toURL().toString());
            SideWinder=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/side_winder.jpg").toURI().toURL().toString());
            IONBurst=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/Ion_burst.jpg").toURI().toURL().toString());
            SniperSupport=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/Shiper Support.jpg").toURI().toURL().toString());
            SonarStrike=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/Sonar Strike.jpg").toURI().toURL().toString());
            toxicrecoil=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/toxic_recoil.jpg").toURI().toURL().toString());
            HolyHeal=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/Specials/Holy_heal.png").toURI().toURL().toString());
            Mover=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/Specials/Mover.jpg").toURI().toURL().toString());
            Scaffholding=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/Specials/Scaffholding.png").toURI().toURL().toString());
            Blackhole=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/Specials/blackHole.png").toURI().toURL().toString());
            TimeGod=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/Specials/TimeGod.png").toURI().toURL().toString());
            Poisonpouch=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/Specials/Poison_pouch.png").toURI().toURL().toString());
            Duplicator=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/Specials/Duplicator.png").toURI().toURL().toString());
            SmokeBomb=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/Specials/Smoke_bomb.png").toURI().toURL().toString());
            StrengthPosion=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/Specials/Strength_poison.png").toURI().toURL().toString());
            shield=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/Specials/shield.png").toURI().toURL().toString());
            multifire=new Image(new File("src/main/resources/com/example/oop_project_phase2/CSS/image/multiFire.png").toURI().toURL().toString());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public Image getImageCard(String name)
    {
        switch (name)
        {
            case "rapid fire":
                return rapidFire;
            case "shock impairment":
                return shockimpairment;
            case "slide swiper":
                return slideswiper;
            case "piercer bullet":
                return piercerbullet;
            case "hiddenshot":
                return hiddenshot;
            case "thermal suppresion":
                return thermalsuppresion;
            case "early percision":
                return earlypercision;
            case "acid cleanser":
                return acidcleanser;
            case "hall storm":
                return hallstorm;
            case "advanced fusion":
                return advancedfusion;
            case "bullet flurry":
                return bulletflurry;
            case "MB silencer":
                return MBsilencer;
            case "Dissolver":
                return Dissolver;
            case "Unshieldable Gamma":
                return UnshieldableGamma;
            case "Side-Winder":
                return SideWinder;
            case "ION Burst":
                return IONBurst;
            case "Sniper Support":
                return SniperSupport;
            case "Sonar Strike":
                return SonarStrike;
            case "toxic recoil":
                return toxicrecoil;
            case "Holy Heal":
                return HolyHeal;
            case "Mover":
                return Mover;
            case "Scaffholding":
                return Scaffholding;
            case "Blackhole":
                return Blackhole;
            case "Time God":
                return TimeGod;
            case "Poison pouch":
                return Poisonpouch;
            case "Duplicator":
                return Duplicator;
            case "Smoke bomb":
                return SmokeBomb;
            case "Strength posion":
                return StrengthPosion;
            case "shield":
                return shield;
            case "multi fire":
                return multifire;
        }
        return null;
    }
}
