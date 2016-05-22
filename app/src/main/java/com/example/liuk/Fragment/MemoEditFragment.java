package com.example.liuk.Fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liuk.Activity.MainActivity;
import com.example.liuk.DB.MemoDB;
import com.example.liuk.myapplication.R;
import com.example.liuk.utils.MediaAdapter;
import com.example.liuk.utils.MediaListCellData;
import com.example.liuk.utils.MyConstants;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MemoEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemoEditFragment extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String MEMO_ID = "param1";
    private static final String TITLE = "param2";
    private static final String CONTENT = "param3";


    public MemoEditFragment() {
        // Required empty public constructor
    }


    /**
     *
     * @param param1 memoId
     * @param param2 title
     * @param param3 content
     * @return
     */
    // TODO: 通过构造方法传递参数
    public static MemoEditFragment newInstance(int param1, String param2, String param3) {
        MemoEditFragment fragment = new MemoEditFragment();
        Bundle args = new Bundle();
        args.putInt(MEMO_ID, param1);
        args.putString(TITLE, param2);
        args.putString(CONTENT,param3);
        fragment.setArguments(args);
        return fragment;
    }

    private int memoId = -1;
    private String memoTitle;
    private String memoCotent;
    TextView textView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: conCreate时将参数取出来赋给变量
        if (getArguments() != null) {
            memoId = getArguments().getInt(MEMO_ID);
            memoTitle = getArguments().getString(TITLE);
            memoCotent = getArguments().getString(CONTENT);

        }

        //TODO: 通过MainActivity找到添加按钮"+",并在onResume激活时改变为选中颜色，onPause时改变为白色
        MainActivity activity = (MainActivity)getActivity();
        textView = (TextView)activity.findViewById(R.id.menu_add);

    }

    private EditText etTitle, etContent;
    private MediaAdapter adapter;

    private MemoDB db;
    private SQLiteDatabase dbRead,dbWrite;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_memo_edit, container, false);

        db = new MemoDB(getActivity());
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();

        //TODO:获取媒体适配器
        adapter = new MediaAdapter(getActivity());
        setListAdapter(adapter);

        //TODO:获取布局中备忘标题和内容控件
        etTitle = (EditText)view.findViewById(R.id.memo_title);
        etContent = (EditText)view.findViewById(R.id.memo_content);

        //TODO:memoId不等于-1说明是从备忘列表中通过点击备忘进入的备忘编辑页面
        if(memoId != -1){
            etTitle.setVisibility(View.VISIBLE);
            //TODO:将传递过来的参数设置为控件的值
            etTitle.setText(memoTitle);
            etContent.setText(memoCotent);
            //Log.e("Edit_memoTitle","标题:"+memoTitle);
            Cursor c = dbRead.query(MemoDB.TABLE_MEDIA,null, MemoDB.COLUMN_MEDIA_OWNER_ID + "=?", new String[] {memoId+""},null,null,null);
            while (c.moveToNext())
            {
                //Log.e("creat","bbbbbbbbbbbb笨笨笨笨笨笨笨笨笨笨笨笨笨笨笨笨吧");
                adapter.add(new MediaListCellData(c.getString(c.getColumnIndex(MemoDB.COLUMN_MEDIA_PATH)),c.getInt(c.getColumnIndex(MemoDB.COLUMN_ID))));
            }
            adapter.notifyDataSetChanged();

        }
        else{
            //Log.e("edit","setInvisible");
            etTitle.setVisibility(View.INVISIBLE);
        }

        view.findViewById(R.id.btnSave).setOnClickListener(btnClickHandler);
        view.findViewById(R.id.btnCancel).setOnClickListener(btnClickHandler);
        view.findViewById(R.id.btnAddVideo).setOnClickListener(btnClickHandler);
        view.findViewById(R.id.btnAddPhoto).setOnClickListener(btnClickHandler);

        return view;
    }

    Intent i;
    File f;
    private  String currentPath = null;
    public static final int REQUEST_CODE_GET_PHOTO = 1;
    public static final int REQUEST_CODE_GET_VIDEO = 2;

    private View.OnClickListener btnClickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnSave:
                    saveMedia(saveNote());
                    //int id = saveNote();
                    //Log.e("id:::","保存"+id);
                    getFragmentManager().popBackStack();
                    //MemoListFragment memoListFragment = new MemoListFragment();
                   // getFragmentManager().beginTransaction().replace(R.id.content_layout,memoListFragment).commit();
                    break;
                case R.id.btnCancel:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.btnAddPhoto:
                    i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    f = new File(getMediaDir(), System.currentTimeMillis() + ".jpg");
                    if(!f.exists()){
                        try {
                            f.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    currentPath = f.getAbsolutePath();
                    i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));

                    startActivityForResult(i,REQUEST_CODE_GET_PHOTO);
                    break;
                case R.id.btnAddVideo:
                    i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    f = new File(getMediaDir(), System.currentTimeMillis() + ".mp4");
                    if(!f.exists()){
                        try {
                            f.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    currentPath = f.getAbsolutePath();
                    i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));

                    startActivityForResult(i,REQUEST_CODE_GET_VIDEO);
                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case REQUEST_CODE_GET_PHOTO:
            case REQUEST_CODE_GET_VIDEO:
                if(resultCode == getActivity().RESULT_OK){
                    // Log.e("ok","djfalkjdflk积分卡角度来看减肥啦看dkkkkkkkkkkkkkkk8888**");
                    adapter.add(new MediaListCellData(currentPath));
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }


    public int saveNote(){
        String title = "";
        Log.e("memoId","记事本ID等于"+memoId);
        if(memoId > -1){
            title = etTitle.getText().toString();
            if(title.length() > 10)
            {
                title = title.substring(0,10).trim() + "...";
            }
        }
       else{
            title = etContent.getText().toString();
            if(title.length() > 10)
            {
                title = title.replaceAll("[\t\n\r]"," ");
                //Log.e("title",title);
                title = title.substring(0,10).trim() + "...";
            }
        }
        ContentValues cv = new ContentValues();
        cv.put(MemoDB.COLUMN_MEMO_TITLE, title);
        cv.put(MemoDB.COLUMN_MEMO_CONTENT,etContent.getText().toString());
        cv.put(MemoDB.COLUMN_MEMO_REMIND_DATE, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));


        if(memoId > -1){
            dbWrite.update(MemoDB.TABLE_MEMO, cv, MemoDB.COLUMN_ID + "=?", new String[] {memoId + ""});
            return memoId;
        }
        else
        {
            return (int)dbWrite.insert(MemoDB.TABLE_MEMO, null, cv);
        }
    }

    public void saveMedia(int nId){
        //Log.e("saveMedia","memoId=" + nId);
        MediaListCellData data;
        ContentValues cv;

        for (int i=0; i<adapter.getCount(); i++)
        {
            data = adapter.getItem(i);
            if(data.getId() <= -1){
                cv = new ContentValues();
                cv.put(MemoDB.COLUMN_MEDIA_PATH, data.getPath());
                cv.put(MemoDB.COLUMN_MEDIA_OWNER_ID, nId);
                dbWrite.insert(MemoDB.TABLE_MEDIA,null, cv);
            }
        }
    }

    public File getMediaDir(){
        File dir = new File(Environment.getExternalStorageDirectory(), "DairyMap");
        if(!dir.exists()){
            dir.mkdirs();
        }
        return dir;
    }

    @Override
    public void onResume() {
        super.onResume();

        //TODO: 通过MainActivity找到添加按钮"+",并在onResume激活时改变为选中颜色
        if(textView != null){
            //TODO:memoId=-1时说明是新添加备忘，快速添加按钮改变为选中颜色，否则不改变颜色
            if(memoId == -1){
                textView.setTextColor(Color.parseColor("#FF6600"));
            }

        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        MediaListCellData data = adapter.getItem(position);
        Intent i;
        switch (data.getType())
        {
            case MyConstants.MEDIA_TYPE_PHOTO:
                i = new Intent(Intent.ACTION_VIEW);
                Log.e("filePath",data.getPath());
                i.setDataAndType(Uri.fromFile(new File(data.getPath())), "image/*");
                // Log.e("savepaht:",data.path);
                startActivity(i);

                break;
            case MyConstants.MEDIA_TYPE_VEDIO:
                i = new Intent(Intent.ACTION_VIEW);
                Log.e("filePath",data.getPath());
                i.setDataAndType(Uri.fromFile(new File(data.getPath())), "video/mp4");
                // Log.e("savepaht:",data.path);
                startActivity(i);

                break;
            default:
                break;
        }
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onPause() {
        super.onPause();

        //TODO: 通过MainActivity找到添加按钮"+",并在onPause时改变为白色
        if(textView != null){
            textView.setTextColor(Color.parseColor("#FFFFFF"));
        }

    }

    @Override
    public void onDestroy() {
        dbRead.close();
        dbWrite.close();
        super.onDestroy();
    }
}
