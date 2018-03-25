package com.example.ferdi.ferdi_1202152160_modul5.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidessence.recyclerviewcursoradapter.RecyclerViewCursorAdapter;
import com.androidessence.recyclerviewcursoradapter.RecyclerViewCursorViewHolder;

import com.example.ferdi.ferdi_1202152160_modul5.App;
import com.example.ferdi.ferdi_1202152160_modul5.R;
import com.example.ferdi.ferdi_1202152160_modul5.data.OsasTodoContract;


//TodoAdapter mewarisi methods dari RecyclerViewCursorAdapter
public class TodoAdapter extends RecyclerViewCursorAdapter<TodoAdapter.ViewHolder> {

    private Context mContext;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //implement viewholder
        return new ViewHolder(mCursorAdapter.newView(mContext, mCursorAdapter.getCursor(), parent));
    }

    //binding atau memasukkan data yang diterima dari activity yang memanggil TodoAdapter
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mCursorAdapter.getCursor().moveToPosition(position);
        setViewHolder(holder);
        mCursorAdapter.bindView(null, mContext, mCursorAdapter.getCursor());
    }

    //class viewholder untuk inisialisasi semua views dan method handler bindCursor
    public class ViewHolder extends RecyclerViewCursorViewHolder {
        public final LinearLayout rlRow;
        public final TextView tvName;
        public final TextView tvDescription;
        public final TextView tvPriorityId;

        ViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvDescription = (TextView) view.findViewById(R.id.tvDescription);
            tvPriorityId = (TextView) view.findViewById(R.id.tvPriorityId);
            rlRow = (LinearLayout) view.findViewById(R.id.rlRow);
        }

        //bindCursor berfungsi untuk menerima parameter cursor dan menerapkannya pada setiap views pada row
        @Override
        public void bindCursor(Cursor cursor) {
            rlRow.setBackgroundColor(Color.GREEN);
            rlRow.setBackgroundColor(App.getWarna(mContext));

            int nameIndex = cursor.getColumnIndex(OsasTodoContract.DaftarInput.COLUMN_NAME); //mengambil index dari kolom name
            final String name = cursor.getString(nameIndex);
            tvName.setText(name);

            int descIndex = cursor.getColumnIndex(OsasTodoContract.DaftarInput.COLUMN_DESCRIPTION); //mengambil index dari kolom description
            final String desc = cursor.getString(descIndex);
            tvDescription.setText(desc);

            int priorityIndex = cursor.getColumnIndex(OsasTodoContract.DaftarInput.COLUMN_PRIORITY); //mengambil index dari kolom priority
            final int priority = cursor.getInt(priorityIndex);
            tvPriorityId.setText(priority + "");
        }
    }

    //TodoAdapter konstruktor
    public TodoAdapter(Context context) {
        super(context);
        mContext = context;
        setupCursorAdapter(null, 0, R.layout.row_todo, false); //set view row layout pada adapter
    }

    //method untuk menghapus item pada recyclerview di adapter dan database
    public void deletePositionItem(int position) {
        mCursorAdapter.getCursor().moveToPosition(position);
        int nameIndex = mCursorAdapter.getCursor().getColumnIndex(OsasTodoContract.DaftarInput._ID);
        final int id = mCursorAdapter.getCursor().getInt(nameIndex);

        //method menghapus data di database dengan content provider where (berdasarkan) id dari item tersebut
        mContext.getContentResolver().delete(OsasTodoContract.DaftarInput.CONTENT_URI, "_id = "+id, null);
    }
}
