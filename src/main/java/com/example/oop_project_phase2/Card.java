package com.example.oop_project_phase2;

import java.util.Objects;
import java.util.Random;

public class Card {
    public boolean handBuff=false;
    public int Duration=0;
    public String name=null;
    public String feature=null;
    public Card cardReference=null;
    public int defence_attack=0;
    public int playerDamage=0;
    public int upgradeLevel=0;
    public double upgradeCost=0;
    public int level = 1;
    String character=null;
    public void upgrade(User user)
    {
        if(user.Coins>=this.upgradeCost && user.Level>=this.upgradeCost)
        {
            user.Coins-=(int) this.upgradeCost;
            this.upgradeCost=1.25*this.upgradeCost;
            this.level++;
            if((this.defence_attack*1.2)<=100)
            {
                this.defence_attack=(int)(defence_attack*1.2);
            }
            else
            {
                this.defence_attack=100;
            }
            if((this.playerDamage*1.05)<=50)
            {
                this.playerDamage=(int)(playerDamage*1.05);
            }
            else
            {
                this.defence_attack=50;
            }
        }
    }

    public Card(String name, int defence_attack, int Duration, int playerDamage, double upgradeCost, int upgradeLevel, String feature, String character,int level) {
        this.level=level;
        this.name = name;
        this.defence_attack = defence_attack;
        this.Duration = Duration;
        if(Duration!=0)
        {
            this.playerDamage = playerDamage / Duration;
        }
        else
        {
            this.playerDamage=0;
        }
        this.upgradeLevel = upgradeLevel;
        this.upgradeCost = upgradeCost;
        this.feature = feature;
        this.character = character;
        this.cardReference=null;
        for(int i=0;i<level-1;i++)
        {
            this.upgradeCost=1.25*this.upgradeCost;
            if((this.defence_attack*1.2)<=100)
            {
                this.defence_attack=(int)(defence_attack*1.2);
            }
            else
            {
                this.defence_attack=100;
            }
            if((this.playerDamage*1.05)<=50)
            {
                this.playerDamage=(int)(playerDamage*1.05);
            }
            else
            {
                this.defence_attack=50;
            }
        }
    }

    public Card(Card card)
    {
        this.name="empty";
        this.cardReference=card;
        if(card!=null)
        {
            this.feature=card.feature;
            this.Duration=card.Duration;
            this.defence_attack=card.defence_attack;
            this.playerDamage=card.playerDamage;
            this.upgradeCost=card.upgradeLevel;
            this.level=card.level;
            this.character=card.character;
        }
        else
        {
            this.Duration=0;
            this.defence_attack=0;
            this.playerDamage=0;
            this.upgradeCost=0;
            this.level=0;
            this.character=null;
        }
    }
    public Card(Card card,Boolean a)
    {
        this.name=card.name;
        this.playerDamage=card.playerDamage;
        this.Duration=card.Duration;
        this.feature=card.feature;
        this.cardReference=null;
        this.defence_attack=card.defence_attack;
        this.upgradeCost=card.upgradeCost;
        this.level=card.level;
        this.character=card.character;
    }
    public void buffInHand(User user)
    {
        Random random=new Random();
        int a;
        int b;
        if(!handBuff)
        {
            if(user.character== User.Character.Dancer)
            {
                if(Objects.equals(this.character, "Dancer"))
                {
                    b=4;
                    a=random.nextInt(b);
                    if(a==0)
                    {
                        this.playerDamage=(int) (1.2*this.playerDamage);
                        handBuff=true;
                    }
                }
            }
            else if(user.character==User.Character.Gunner)
            {
                if(Objects.equals(this.character,"Gunner"))
                {
                    b=6;
                    a=random.nextInt(b);
                    if(a==0)
                    {
                        this.playerDamage=(int) (1.2*this.playerDamage);
                        handBuff=true;
                    }
                }
            }
            else if(user.character==User.Character.Fighter)
            {
                if(Objects.equals(this.character,"Fighter"))
                {
                    b=10;
                    a=random.nextInt(b);
                    if(a==0)
                    {
                        this.playerDamage=(int) (1.2*this.playerDamage);
                        handBuff=true;
                    }
                }
            }
            else if(user.character==User.Character.Wizard)
            {
                if(Objects.equals(this.character,"Wizard"))
                {
                    b=8;
                    a=random.nextInt(b);
                    if(a==0)
                    {
                        this.playerDamage=(int) (1.2*this.playerDamage);
                        handBuff=true;
                    }
                }
            }
        }
    }
    public void upgradeCharacter(User user)
    {
        if(user.character== User.Character.Dancer)
        {
            if(Objects.equals(this.character, "Dancer"))
            {
                this.playerDamage=(int) (this.playerDamage*1.1);
            }
        }
        else if(user.character==User.Character.Gunner)
        {
            if(Objects.equals(this.character,"Gunner"))
            {
                this.playerDamage=(int) (this.playerDamage*1.1);
            }
        }
        else if(user.character==User.Character.Fighter)
        {
            if(Objects.equals(this.character,"Fighter"))
            {
                this.playerDamage=(int) (this.playerDamage*1.1);
            }
        }
        else if(user.character==User.Character.Wizard)
        {
            if(Objects.equals(this.character,"Wizard"))
            {
                this.playerDamage=(int) (this.playerDamage*1.1);
            }
        }
    }
}
