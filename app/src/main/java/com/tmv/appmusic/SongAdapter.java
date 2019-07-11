package com.tmv.appmusic;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private Context context;
    private ArrayList<SongInfo> songInfos;

    OnitemClickListener onitemClickListener;//tự gõ

    public SongAdapter(Context context, ArrayList<SongInfo> songInfos) {
        this.context = context;
        this.songInfos = songInfos;
    }

    //tự gõ
    public interface OnitemClickListener{
        void OnitemClick(Button b, View v, SongInfo obj, int position) throws IOException;

    }
    //tự gõ
    public void setOnitemClickListener (OnitemClickListener onitemClickListener){
        this.onitemClickListener = onitemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.raw_song, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final SongInfo c = songInfos.get(i);
        viewHolder.tvSongName.setText(c.songName);
        viewHolder.tvArtistName.setText(c.artistName);
        viewHolder.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onitemClickListener != null){
                    onitemClickListener.OnitemClick(viewHolder.btnPlay, v, c, i);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return songInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //khai báo
        TextView tvSongName, tvArtistName;
        Button btnPlay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //ánh xạ, có thể bỏ (TextView) (Button) đằng trước
            tvSongName = (TextView)itemView.findViewById(R.id.tvSongName);
            tvArtistName = (TextView)itemView.findViewById(R.id.tvArtistName);
            btnPlay = (Button)itemView.findViewById(R.id.btnPlay);




        }
    }
}
