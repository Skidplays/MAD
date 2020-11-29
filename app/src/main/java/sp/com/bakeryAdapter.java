package sp.com;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class bakeryAdapter extends ArrayAdapter<String> {

    private String bakeryName[];
    private Integer drawables[];
    private String desc[];
    private double lat[];
    private double lon[];
    private String addr[];
    private Context mcontext;
    public bakeryAdapter(Context context, String[] bakeryName, Integer[] drawables, String[] addr ,String[] desc,double[] lat, double[] lon   ) {
        super(context, R.layout.row, bakeryName);

        this.mcontext= context;
        this.bakeryName = bakeryName;
        this.drawables = drawables;
        this.desc = desc;
        this.lat = lat;
        this.lon = lon;
        this.addr = addr;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder = new ViewHolder();
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row, parent, false);
            viewholder.mbakeryname = convertView.findViewById(R.id.bakeryname);
            viewholder.mbakerydesc = convertView.findViewById(R.id.desc);
            viewholder.mbakerylogo = convertView.findViewById(R.id.icon);
            convertView.setTag(viewholder);
        }else{
            viewholder = (ViewHolder)convertView.getTag();
        }
        viewholder.mbakeryname.setText(bakeryName[position]);
        viewholder.mbakerydesc.setText(desc[position]+"\n\n"+addr[position]);
        viewholder.mbakerylogo.setImageResource(drawables[position]);

        return convertView;
    }

    static class ViewHolder {
        ImageView mbakerylogo;
        TextView mbakeryname;
        TextView mbakerydesc;
    }
}
