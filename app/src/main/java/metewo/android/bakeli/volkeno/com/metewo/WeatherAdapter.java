package metewo.android.bakeli.volkeno.com.metewo;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.ForecastDay;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<ForecastDay> fdays = new ArrayList<>();
    private Context context;
    private MainActivity mainActivity;

    public WeatherAdapter(Context context,List<ForecastDay> wList) {
        this.fdays = wList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Picasso
                .with(context).load("http:"+fdays.get(position).getDay().getCondition().getIcon())
                .resize(120,120)
                .into(holder.icon);

        //samedi
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(cal1.getTime());
        cal1.add(Calendar.DATE, 2);
        Date d1 = cal1.getTime();
        String dayOfTheWeek1 = (String) DateFormat.format("EEEE", d1);

        //dimanche
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(cal2.getTime());
        cal2.add(Calendar.DATE, 3);
        Date d2 = cal2.getTime();
        String dayOfTheWeek2 = (String) DateFormat.format("EEEE", d2);

        //lundi
        Calendar cal3 = Calendar.getInstance();
        cal3.setTime(cal3.getTime());
        cal3.add(Calendar.DATE, 4);
        Date d3 = cal3.getTime();
        String dayOfTheWeek3 = (String) DateFormat.format("EEEE", d3);


        String jour = fdays.get(position).getDate();
        if(position==0)
        {
            jour = "Demain";
            //holder.day.setText(jour);#ec5747
            holder.itemView.setBackgroundColor(Color.parseColor("#ec5747"));
        } else
         if(position==1)
        {
            jour = dayOfTheWeek1;
            holder.itemView.setBackgroundColor(Color.parseColor("#8cace1"));
        }else
        if(position==2)
        {
            jour = dayOfTheWeek2;
            holder.itemView.setBackgroundColor(Color.parseColor("#7ea2dd"));
        }else
        if(position==3)
        {
            jour = dayOfTheWeek3;
            holder.itemView.setBackgroundColor(Color.parseColor("#ff5f4e"));
        }
        holder.day.setText(jour);
        holder.condition.setText(fdays.get(position).getDay().getCondition().getText());
        holder.mintemp.setText(""+fdays.get(position).getDay().getMintempC().intValue());
        holder.maxtemp.setText(""+fdays.get(position).getDay().getMaxtempC().intValue());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return fdays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView day, condition, mintemp, maxtemp;
        private ImageView icon;

        public ViewHolder(View view) {
            super(view);
            day = (TextView) view.findViewById(R.id.day);
            condition = (TextView) view.findViewById(R.id.condition);
            mintemp = (TextView) view.findViewById(R.id.tempMin);
            maxtemp = (TextView) view.findViewById(R.id.tempMax);
            icon = (ImageView) view.findViewById(R.id.icon);
        }
    }
}