package com.smn.rental;

public class ImageList {

    public static int getImageFromName(String imagename){
        int pic = R.drawable.nopic;
        if(imagename.equals("mobile1.jpg")){
            pic = R.drawable.mobile1;
        }else if(imagename.equals("mobile2.jpg")){
            pic = R.drawable.mobile2;
        }else if(imagename.equals("mobile3.jpg")){
            pic = R.drawable.mobile3;
        }else if(imagename.equals("mobile4.jpg")){
            pic = R.drawable.mobile4;
        }else if(imagename.equals("mobile5.jpg")){
            pic = R.drawable.mobile5;
        }else if(imagename.equals("mobile6.jpg")){
            pic = R.drawable.mobile6;
        }else if(imagename.equals("mobile7.jpg")){
            pic = R.drawable.mobile7;
        }
        return pic;
    }
}
