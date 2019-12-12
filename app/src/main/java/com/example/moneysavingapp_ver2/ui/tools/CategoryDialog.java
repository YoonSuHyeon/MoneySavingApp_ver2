package com.example.moneysavingapp_ver2.ui.tools;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.moneysavingapp_ver2.R;

public class CategoryDialog extends DialogFragment {
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    Button btn_Ok,btn_Cancel;
    DialogCategoryResult result;
    String category_name;
    private Fragment fragment;
    public CategoryDialog() {

    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = 1000;
        params.height = 900;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams)params);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view =inflater.inflate(R.layout.category,container,false);
        super.setCancelable(false);

        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("category_approval");

        t1=view.findViewById(R.id.gmddjqaus); //흥업면
        t2=view.findViewById(R.id.eksrPehd);//단계동
        t3=view.findViewById(R.id.eksrnehd); //단계동
        t4=view.findViewById(R.id.antlfehd); //무실동
        t5=view.findViewById(R.id.xowkdehd); //태장동
        t6=view.findViewById(R.id.dntksehd); //우산동
        t7=view.findViewById(R.id.audfbs1ehd); //명륜 1동
        t8=view.findViewById(R.id.audfbs2ehd); //명륜 2동
        t9=view.findViewById(R.id.qksrhrehd); //반곡동
        t10=view.findViewById(R.id.godrnehd); //행구동
        btn_Ok = view.findViewById(R.id.btn_categoryOk);
        btn_Cancel = view.findViewById(R.id.btn_categoryCancel);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_name = t1.getText().toString();
                Toast.makeText(view.getContext(),category_name+"이 선택 되었습니다",Toast.LENGTH_SHORT).show();
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_name = t2.getText().toString();
                Toast.makeText(view.getContext(),category_name+"이 선택 되었습니다",Toast.LENGTH_SHORT).show();
            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_name = t3.getText().toString();
                Toast.makeText(view.getContext(),category_name+"이 선택 되었습니다",Toast.LENGTH_SHORT).show();
            }
        });

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_name = t4.getText().toString();
                Toast.makeText(view.getContext(),category_name+"이 선택 되었습니다",Toast.LENGTH_SHORT).show();
            }
        });

        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_name = t5.getText().toString();
                Toast.makeText(view.getContext(),category_name+"이 선택 되었습니다",Toast.LENGTH_SHORT).show();
            }
        });

        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_name = t6.getText().toString();
                Toast.makeText(view.getContext(),category_name+"이 선택 되었습니다",Toast.LENGTH_SHORT).show();
            }
        });

        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_name = t7.getText().toString();
                Toast.makeText(view.getContext(),category_name+"이 선택 되었습니다",Toast.LENGTH_SHORT).show();
            }
        });

        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_name = t8.getText().toString();
                Toast.makeText(view.getContext(),category_name+"이 선택 되었습니다",Toast.LENGTH_SHORT).show();
            }
        });

        t9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_name = t9.getText().toString();
                Toast.makeText(view.getContext(),category_name+"이 선택 되었습니다",Toast.LENGTH_SHORT).show();
            }
        });

        t10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_name = t10.getText().toString();
                Toast.makeText(view.getContext(),category_name+"이 선택 되었습니다",Toast.LENGTH_SHORT).show();
            }
        });

        btn_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(category_name !=null){
                    result.finish(category_name);
                    DialogFragment dialogFragment = (DialogFragment)fragment;
                    dialogFragment.dismiss();
                }
                else{
                    Toast.makeText(view.getContext(),"원하시는 카테고리를 먼저 선택해주세요",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = (DialogFragment)fragment;
                dialogFragment.dismiss();
                category_name = null;
            }
        });
        return view;
    }

    public void setResult(DialogCategoryResult result){
        this.result=result;
    }

    public interface DialogCategoryResult{
        void finish(String result);
    }
}
