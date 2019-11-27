package com.example.chungchunpay.menu;

import android.graphics.Color;
import android.util.Pair;

import com.example.chungchunpay.R;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.nightonke.boommenu.Util;

import java.util.ArrayList;
import java.util.List;

public class BoomMenuBuilderManager {
    private static int[] imageResources = new int[]{
            R.drawable.tour,
            R.drawable.food,
            R.drawable.bus,
            R.drawable.taxi,
            R.drawable.gs,
            R.drawable.bank,
            R.drawable.fingerprint,
            R.drawable.home,
            R.drawable.ic_home_outline_grey600_24dp,
            R.drawable.ic_logout_grey600_24dp,
            R.drawable.image,
            R.drawable.logo,
            R.drawable.user,
            R.drawable.menu,
            R.drawable.menu_my,
            R.drawable.up_arrow
    };

    private static int imageResourceIndex = 0;

    public static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    public static SimpleCircleButton.Builder getSimpleCircleButtonBuilder() {
        return new SimpleCircleButton.Builder()
                .normalImageRes(getImageResource());
    }

    public static SimpleCircleButton.Builder getSquareSimpleCircleButtonBuilder() {
        return new SimpleCircleButton.Builder()
                .isRound(false)
                .shadowCornerRadius(Util.dp2px(20))
                .buttonCornerRadius(Util.dp2px(20))
                .normalImageRes(getImageResource());
    }

    public static TextInsideCircleButton.Builder getTextInsideCircleButtonBuilder(int title) {
        switch (title){
            case 1 :
                return new TextInsideCircleButton.Builder()
                        .normalImageRes(getImageResource())
                        .normalTextRes(R.string.text_inside_circle_button_text_tour);
            case 2 :
                return new TextInsideCircleButton.Builder()
                        .normalImageRes(getImageResource())
                        .normalTextRes(R.string.text_inside_circle_button_text_food);
            case 3 :
                return new TextInsideCircleButton.Builder()
                        .normalImageRes(getImageResource())
                        .normalTextRes(R.string.text_inside_circle_button_text_bus);
            case 4 :
                return new TextInsideCircleButton.Builder()
                        .normalImageRes(getImageResource())
                        .normalTextRes(R.string.text_inside_circle_button_text_taxi);
            case 5 :
                return new TextInsideCircleButton.Builder()
                        .normalImageRes(getImageResource())
                        .normalTextRes(R.string.text_inside_circle_button_text_gs);

            case 6 :
                return new TextInsideCircleButton.Builder()
                        .normalImageRes(getImageResource())
                        .normalTextRes(R.string.text_inside_circle_button_text_bank);

        }
        return new TextInsideCircleButton.Builder()
                .normalImageRes(getImageResource())
                .normalTextRes(R.string.text_inside_circle_button_text_tour);
    }

    public static TextInsideCircleButton.Builder getSquareTextInsideCircleButtonBuilder() {
        return new TextInsideCircleButton.Builder()
                .isRound(false)
                .shadowCornerRadius(Util.dp2px(10))
                .buttonCornerRadius(Util.dp2px(10))
                .normalImageRes(getImageResource())
                .normalTextRes(R.string.text_inside_circle_button_text_normal);
    }

    public static TextInsideCircleButton.Builder getTextInsideCircleButtonBuilderWithDifferentPieceColor() {
        return new TextInsideCircleButton.Builder()
                .normalImageRes(getImageResource())
                .normalTextRes(R.string.text_inside_circle_button_text_normal)
                .pieceColor(Color.WHITE);
    }

    public static TextOutsideCircleButton.Builder getTextOutsideCircleButtonBuilder(int title) {
        switch (title){
            case 1 :
                return new TextOutsideCircleButton.Builder()
                        .normalImageRes(getImageResource())
                        .normalTextRes(R.string.text_inside_circle_button_text_tour);
            case 2 :
                return new TextOutsideCircleButton.Builder()
                        .normalImageRes(getImageResource())
                        .normalTextRes(R.string.text_inside_circle_button_text_food);
            case 3 :
                return new TextOutsideCircleButton.Builder()
                        .normalImageRes(getImageResource())
                        .normalTextRes(R.string.text_inside_circle_button_text_bus);
            case 4 :
                return new TextOutsideCircleButton.Builder()
                        .normalImageRes(getImageResource())
                        .normalTextRes(R.string.text_inside_circle_button_text_taxi);
            case 5 :
                return new TextOutsideCircleButton.Builder()
                        .normalImageRes(getImageResource())
                        .normalTextRes(R.string.text_inside_circle_button_text_gs);
            case 6 :
                return new TextOutsideCircleButton.Builder()
                        .normalImageRes(getImageResource())
                        .normalTextRes(R.string.text_inside_circle_button_text_bank);
                default: break;
        }
        return new TextOutsideCircleButton.Builder()
                .normalImageRes(getImageResource())
                .normalTextRes(R.string.text_inside_circle_button_text_bank);
    }

    public static TextOutsideCircleButton.Builder getSquareTextOutsideCircleButtonBuilder() {
        return new TextOutsideCircleButton.Builder()
                .isRound(false)
                .shadowCornerRadius(Util.dp2px(15))
                .buttonCornerRadius(Util.dp2px(15))
                .normalImageRes(getImageResource())
                .normalTextRes(R.string.text_outside_circle_button_text_normal);
    }

    public static TextOutsideCircleButton.Builder getTextOutsideCircleButtonBuilderWithDifferentPieceColor() {
        return new TextOutsideCircleButton.Builder()
                .normalImageRes(getImageResource())
                .normalTextRes(R.string.text_outside_circle_button_text_normal)
                .pieceColor(Color.WHITE);
    }

    public static HamButton.Builder getHamButtonBuilder() {
        return new HamButton.Builder()
                .normalImageRes(getImageResource())
                .normalTextRes(R.string.text_ham_button_text_normal)
                .subNormalTextRes(R.string.text_ham_button_sub_text_normal);
    }

    public static HamButton.Builder getHamButtonBuilder(String text, String subText) {
        return new HamButton.Builder()
                .normalImageRes(getImageResource())
                .normalText(text)
                .subNormalText(subText);
    }

    public static HamButton.Builder getPieceCornerRadiusHamButtonBuilder() {
        return new HamButton.Builder()
                .normalImageRes(getImageResource())
                .normalTextRes(R.string.text_ham_button_text_normal)
                .subNormalTextRes(R.string.text_ham_button_sub_text_normal);
    }

    public static HamButton.Builder getHamButtonBuilderWithDifferentPieceColor() {
        return new HamButton.Builder()
                .normalImageRes(getImageResource())
                .normalTextRes(R.string.text_ham_button_text_normal)
                .subNormalTextRes(R.string.text_ham_button_sub_text_normal)
                .pieceColor(Color.WHITE);
    }

    public static List<String> getCircleButtonData(ArrayList<Pair> piecesAndButtons) {
        List<String> data = new ArrayList<>();
        for (int p = 0; p < PiecePlaceEnum.values().length - 1; p++) {
            for (int b = 0; b < ButtonPlaceEnum.values().length - 1; b++) {
                PiecePlaceEnum piecePlaceEnum = PiecePlaceEnum.getEnum(p);
                ButtonPlaceEnum buttonPlaceEnum = ButtonPlaceEnum.getEnum(b);
                if (piecePlaceEnum.pieceNumber() == buttonPlaceEnum.buttonNumber()
                        || buttonPlaceEnum == ButtonPlaceEnum.Horizontal
                        || buttonPlaceEnum == ButtonPlaceEnum.Vertical) {
                    piecesAndButtons.add(new Pair<>(piecePlaceEnum, buttonPlaceEnum));
                    data.add(piecePlaceEnum + " " + buttonPlaceEnum);
                    if (piecePlaceEnum == PiecePlaceEnum.HAM_1
                            || piecePlaceEnum == PiecePlaceEnum.HAM_2
                            || piecePlaceEnum == PiecePlaceEnum.HAM_3
                            || piecePlaceEnum == PiecePlaceEnum.HAM_4
                            || piecePlaceEnum == PiecePlaceEnum.HAM_5
                            || piecePlaceEnum == PiecePlaceEnum.HAM_6
                            || piecePlaceEnum == PiecePlaceEnum.Share
                            || piecePlaceEnum == PiecePlaceEnum.Custom
                            || buttonPlaceEnum == ButtonPlaceEnum.HAM_1
                            || buttonPlaceEnum == ButtonPlaceEnum.HAM_2
                            || buttonPlaceEnum == ButtonPlaceEnum.HAM_3
                            || buttonPlaceEnum == ButtonPlaceEnum.HAM_4
                            || buttonPlaceEnum == ButtonPlaceEnum.HAM_5
                            || buttonPlaceEnum == ButtonPlaceEnum.HAM_6
                            || buttonPlaceEnum == ButtonPlaceEnum.Custom) {
                        piecesAndButtons.remove(piecesAndButtons.size() - 1);
                        data.remove(data.size() - 1);
                    }
                }
            }
        }
        return data;
    }

    public static List<String> getHamButtonData(ArrayList<Pair> piecesAndButtons) {
        List<String> data = new ArrayList<>();
        for (int p = 0; p < PiecePlaceEnum.values().length - 1; p++) {
            for (int b = 0; b < ButtonPlaceEnum.values().length - 1; b++) {
                PiecePlaceEnum piecePlaceEnum = PiecePlaceEnum.getEnum(p);
                ButtonPlaceEnum buttonPlaceEnum = ButtonPlaceEnum.getEnum(b);
                if (piecePlaceEnum.pieceNumber() == buttonPlaceEnum.buttonNumber()
                        || buttonPlaceEnum == ButtonPlaceEnum.Horizontal
                        || buttonPlaceEnum == ButtonPlaceEnum.Vertical) {
                    piecesAndButtons.add(new Pair<>(piecePlaceEnum, buttonPlaceEnum));
                    data.add(piecePlaceEnum + " " + buttonPlaceEnum);
                    if (piecePlaceEnum.getValue() < PiecePlaceEnum.HAM_1.getValue()
                            || piecePlaceEnum == PiecePlaceEnum.Share
                            || piecePlaceEnum == PiecePlaceEnum.Custom
                            || buttonPlaceEnum.getValue() < ButtonPlaceEnum.HAM_1.getValue()) {
                        piecesAndButtons.remove(piecesAndButtons.size() - 1);
                        data.remove(data.size() - 1);
                    }
                }
            }
        }
        return data;
    }

    private static BoomMenuBuilderManager ourInstance = new BoomMenuBuilderManager();

    public static BoomMenuBuilderManager getInstance() {
        return ourInstance;
    }

    private BoomMenuBuilderManager() {
    }
}
