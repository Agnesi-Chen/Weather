package com.example.weather.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.weather.R;
import java.util.ArrayList;
import java.util.List;

public class SuggestionActivity extends Activity {

    private Button back,send;
    private RecyclerView recyclerView;
    private EditText msg;
    private List<MsgEntity> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        init();
        initMsg();
        final MsgAdapter msgAdapter = new MsgAdapter(this,list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(msgAdapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String send_content = msg.getText().toString().trim();
                if (!TextUtils.isEmpty(send_content)){
                    MsgEntity send_msg = new MsgEntity(MsgEntity.SEND_MSG,"  "+send_content+"  ");
                    list.add(send_msg);
                    //刷新RecyclerView显示
                    msgAdapter.notifyItemInserted(list.size()-1);
                    //模拟接受消息
                    MsgEntity rcv_msg = new MsgEntity(MsgEntity.RCV_MSG,"  感谢您的建议！我们将尽快处理！  ");
                    list.add(rcv_msg);
                    msgAdapter.notifyItemInserted(list.size()-1);
                    //将RecyclerView将显示的数据定位到最后一行
                    //recyclerView.scrollToPosition(list.size()-1);
                    msg.setText("");//清空消息输入框
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到上一界面
                Intent intent = new Intent(SuggestionActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
    //初始化
    private void initMsg() {
        list = new ArrayList<MsgEntity>();
    }
    private void init() {
        back = findViewById(R.id.back);
        recyclerView = findViewById(R.id.recyclerView);
        msg = findViewById(R.id.msg);
        send = findViewById(R.id.send);
    }
    //返回键方法的重写
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keycode, event);
    }
    //对话框的实现
    public class MsgEntity{
        public static final int SEND_MSG = 1;
        public static final int RCV_MSG = 2;

        private String content;
        private int type;

        public MsgEntity(int type, String content) {
            this.type = type;
            this.content = content;
        }
        public MsgEntity() {
        }
        public String getContent() {
            return content;
        }
        public int getType() {
            return type;
        }
    }

    public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
        private List<MsgEntity> mMsg;//消息的实体类集合
        private Context mContext;

        public MsgAdapter(Context context, List<MsgEntity> msg) {
            this.mMsg = msg;
            this.mContext = context;
        }

        @Override
        public MsgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.items, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MsgAdapter.ViewHolder holder, int position) {
            MsgEntity msg = mMsg.get(position);
            if (msg.getType() == MsgEntity.RCV_MSG) {
                //接受消息:让发送消息有关的控件隐藏
                holder.send_layout.setVisibility(View.GONE);
                holder.rev_layout.setVisibility(View.VISIBLE);
                holder.rev_tv.setText(msg.getContent());
            } else if (msg.getType() == MsgEntity.SEND_MSG) {
                //发送消息:让接收消息有关的控件隐藏
                holder.rev_layout.setVisibility(View.GONE);
                holder.send_layout.setVisibility(View.VISIBLE);
                holder.send_tv.setText(msg.getContent());
            }
        }

        @Override
        public int getItemCount() {
            return mMsg.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            LinearLayout rev_layout;
            LinearLayout send_layout;
            TextView rev_tv;
            TextView send_tv;

            public ViewHolder(View itemView) {
                super(itemView);
                rev_layout = itemView.findViewById(R.id.rev_layout);
                send_layout = itemView.findViewById(R.id.send_layout);
                rev_tv = itemView.findViewById(R.id.rev_tv);
                send_tv = itemView.findViewById(R.id.send_tv);
            }
        }
    }
}