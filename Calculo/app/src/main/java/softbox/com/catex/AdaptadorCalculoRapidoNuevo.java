package softbox.com.catex;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorCalculoRapidoNuevo extends BaseAdapter {
    private Context contexto;
    private ArrayList<CalculoRapidoNuevoItem> listItems;

    public AdaptadorCalculoRapidoNuevo(Context contexto, ArrayList<CalculoRapidoNuevoItem> listItems) {
        this.contexto = contexto;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public  Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CalculoRapidoNuevoItem item = (CalculoRapidoNuevoItem) getItem(position);

        convertView = LayoutInflater.from(contexto).inflate(R.layout.elemento_lista_calculo_rapido_nuevo,null);
        ImageButton boton = (ImageButton) convertView.findViewById(R.id.basura);
        EditText valor = (EditText) convertView.findViewById(R.id.valor);
        EditText porcentaje = (EditText) convertView.findViewById(R.id.porcentaje);

        valor.setText(item.getValor());
        porcentaje.setText(item.getPorcentaje());
        boton.setImageResource(item.getBoton());
        cambios(item,valor,porcentaje,boton);

        return convertView;
    }

    private void cambios(final CalculoRapidoNuevoItem item, final EditText valor, final EditText porcentaje, ImageButton boton) {
        valor.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Fires right as the text is being changed (even supplies the range of text)

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // Fires right before text is changing
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Fires right after the text has changed
                item.setValor(valor.getText().toString());
            }
        });

        porcentaje.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Fires right as the text is being changed (even supplies the range of text)

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // Fires right before text is changing
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Fires right after the text has changed
                item.setPorcentaje(porcentaje.getText().toString());
            }
        });
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }
}
