package metewo.android.bakeli.volkeno.com.metewo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import model.ForecastDay;
import model.Wether;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<ForecastDay> fdays = new ArrayList<>();
    private Context context;

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
        //ForecastDay fday = fdays.get(position);
        holder.day.setText(fdays.get(position).getDate());
        holder.condition.setText(fdays.get(position).getDay().getCondition().getText());
        holder.mintemp.setText(fdays.get(position).getDay().getMintempC());
        holder.maxtemp.setText(fdays.get(position).getDay().getMaxtempC());

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

        public ViewHolder(View view) {
            super(view);

            day = (TextView) view.findViewById(R.id.day);
            condition = (TextView) view.findViewById(R.id.condition);
            mintemp = (TextView) view.findViewById(R.id.tempMin);
            maxtemp = (TextView) view.findViewById(R.id.tempMax);

        }
    }
}