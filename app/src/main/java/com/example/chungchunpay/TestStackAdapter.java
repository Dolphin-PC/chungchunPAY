package com.example.chungchunpay;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.StackAdapter;

public class TestStackAdapter extends StackAdapter<Integer> {

    public TestStackAdapter(Context context) {
        super(context);
    }

    @Override
    public void bindView(Integer data, int position, CardStackView.ViewHolder holder) {
        if (holder instanceof ColorItemLargeHeaderViewHolder) {
            ColorItemLargeHeaderViewHolder h = (ColorItemLargeHeaderViewHolder) holder;
            h.onBind(data, position);
        }
        if (holder instanceof ColorItemWithNoHeaderViewHolder) {
            ColorItemWithNoHeaderViewHolder h = (ColorItemWithNoHeaderViewHolder) holder;
            h.onBind(data, position);
        }
        if (holder instanceof ColorItemViewHolder) {
            ColorItemViewHolder h = (ColorItemViewHolder) holder;
            h.onBind(data, position);
        }
    }

    @Override
    protected CardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case R.layout.list_card_item_larger_header:
                view = getLayoutInflater().inflate(R.layout.list_card_item_larger_header, parent, false);
                return new ColorItemLargeHeaderViewHolder(view);
            case R.layout.list_card_item_with_no_header:
                view = getLayoutInflater().inflate(R.layout.list_card_item_with_no_header, parent, false);
                return new ColorItemWithNoHeaderViewHolder(view);
            default:
                view = getLayoutInflater().inflate(R.layout.list_card_item, parent, false);
                return new ColorItemViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 6) {//TODO TEST LARGER ITEM
            return R.layout.list_card_item_larger_header;
        } else if (position == 10) {
            return R.layout.list_card_item_with_no_header;
        }else {
            return R.layout.list_card_item;
        }
    }

    static class ColorItemViewHolder extends CardStackView.ViewHolder {
        View mLayout;
        View mContainerContent;
        TextView mTextTitle,TextView,CardTitleText;

        public ColorItemViewHolder(View view) {
            super(view);
            mLayout = view.findViewById(R.id.frame_list_card_item);
            mContainerContent = view.findViewById(R.id.container_list_content);
            mTextTitle = (TextView) view.findViewById(R.id.text_list_card_title);
            TextView = view.findViewById(R.id.text_view);
        }

        @Override
        public void onItemExpand(boolean b) {
            mContainerContent.setVisibility(b ? View.VISIBLE : View.GONE);
        }

        public void onBind(Integer data, int position) {
            mLayout.getBackground().setColorFilter(ContextCompat.getColor(getContext(), data), PorterDuff.Mode.SRC_IN);
            mTextTitle.setText(String.valueOf(position));

        }

    }

    static class ColorItemWithNoHeaderViewHolder extends CardStackView.ViewHolder {
        View mLayout;
        TextView mTextTitle,textView, CardTitleText;

        public ColorItemWithNoHeaderViewHolder(View view) {
            super(view);
            mLayout = view.findViewById(R.id.frame_list_card_item);
            mTextTitle = (TextView) view.findViewById(R.id.text_list_card_title);
            textView = view.findViewById(R.id.text_view);
            CardTitleText = view.findViewById(R.id.CardTitleText);
        }

        @Override
        public void onItemExpand(boolean b) {
        }

        public void onBind(Integer data, int position) {
            mLayout.getBackground().setColorFilter(ContextCompat.getColor(getContext(), data), PorterDuff.Mode.SRC_IN);
            mTextTitle.setText(String.valueOf(position));
            switch (position){
                case 0 :
                    CardTitleText.setText("춘천사랑상품권");
                    textView.setText("춘천사랑상품권은 '강원도 춘천시'에서 발행하고 사용할 수 있는 상품권입니다.");
                    break;
                case 1 :
                    CardTitleText.setText("온누리상품권");
                    textView.setText("온누리상품권은 전통시장에서 사용할 수 있는 상품권입니다.");
                    break;
                case 2 :
                    CardTitleText.setText("문화상품권");
                    textView.setText("문화상품권은 한국문화진흥원에서 발행하는 상품권으로 온라인에서 사용할 수 있는 상품권입니다.");
                    break;
                case 3 :
                    CardTitleText.setText("신세계상품권");
                    textView.setText("신세계 계열사 및 제휴된 가맹점에서 사용할 수 있는 상품권으로, 대표적으로 전국 이마트, 신세계백화점 등에서 사용할 수 있는 상품권입니다.");
                    break;
            }
        }

    }

    static class ColorItemLargeHeaderViewHolder extends CardStackView.ViewHolder {
        View mLayout;
        View mContainerContent;
        TextView mTextTitle,CardTitleText,textView;

        public ColorItemLargeHeaderViewHolder(View view) {
            super(view);
            mLayout = view.findViewById(R.id.frame_list_card_item);
            mContainerContent = view.findViewById(R.id.container_list_content);
            mTextTitle = (TextView) view.findViewById(R.id.text_list_card_title);
            CardTitleText = view.findViewById(R.id.CardTitleText);
            textView = view.findViewById(R.id.text_view);

        }

        @Override
        public void onItemExpand(boolean b) {
            mContainerContent.setVisibility(b ? View.VISIBLE : View.GONE); //pre , next 버튼
        }

        @Override
        protected void onAnimationStateChange(int state, boolean willBeSelect) {
            super.onAnimationStateChange(state, willBeSelect);
            if (state == CardStackView.ANIMATION_STATE_START && willBeSelect) {
                onItemExpand(true);
            }
            if (state == CardStackView.ANIMATION_STATE_END && !willBeSelect) {
                onItemExpand(false);
            }
        }

        public void onBind(Integer data, int position) {
            mLayout.getBackground().setColorFilter(ContextCompat.getColor(getContext(), data), PorterDuff.Mode.SRC_IN);
            mTextTitle.setText(String.valueOf(position));

            itemView.findViewById(R.id.text_view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((CardStackView)itemView.getParent()).performItemClick(ColorItemLargeHeaderViewHolder.this);
                }
            });
            switch (position){
                case 0 :
                    CardTitleText.setText("춘천사랑상품권");
                    textView.setText("춘천사랑상품권은 '강원도 춘천시'에서 발행하고 사용할 수 있는 상품권입니다.");
                    break;
                case 1 :
                    CardTitleText.setText("온누리상품권");
                    textView.setText("온누리상품권은 전통시장에서 사용할 수 있는 상품권입니다.");
                    break;
                case 2 :
                    CardTitleText.setText("문화상품권");
                    textView.setText("문화상품권은 한국문화진흥원에서 발행하는 상품권으로 온라인에서 사용할 수 있는 상품권입니다.");
                    break;
                case 3 :
                    CardTitleText.setText("신세계상품권");
                    textView.setText("신세계 계열사 및 제휴된 가맹점에서 사용할 수 있는 상품권으로, 대표적으로 전국 이마트, 신세계백화점 등에서 사용할 수 있는 상품권입니다.");
                    break;
            }
        }

    }

}

//TODO : StackCardView TextView 맞추기