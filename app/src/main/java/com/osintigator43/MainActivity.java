package com.osintigator43;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;

public class MainActivity extends Activity {

    private LinearLayout content;
    private EditText searchInput;

    private int bg = Color.rgb(15, 20, 29);
    private int panel = Color.rgb(27, 36, 49);
    private int blue = Color.rgb(65, 170, 240);
    private int white = Color.WHITE;
    private int muted = Color.rgb(165, 180, 200);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(bg);

        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(28, 64, 28, 36);

        scroll.addView(content);
        setContentView(scroll);

        TextView title = new TextView(this);
        title.setText("OSINTigator43");
        title.setTextColor(blue);
        title.setTextSize(29);
        title.setTypeface(null, Typeface.BOLD);
        content.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("OPEN SOURCE INTELLIGENCE");
        subtitle.setTextColor(muted);
        subtitle.setTextSize(12);
        subtitle.setPadding(0, 4, 0, 30);
        content.addView(subtitle);

        addLabel("EMAIL INTELLIGENCE");

        searchInput = new EditText(this);
        searchInput.setSingleLine(true);
        searchInput.setHint("Introduz um email ou username");
        searchInput.setHintTextColor(muted);
        searchInput.setTextColor(white);
        searchInput.setTextSize(15);
        searchInput.setPadding(18, 14, 18, 14);
        searchInput.setBackground(makeBackground(panel, 14));
        content.addView(searchInput, params(-1, -2, 0, 0, 0, 14));

        addButton("Pesquisar email", v -> searchEmail());
        addButton("Pesquisar username", v -> searchUsername());
        addButton("Pesquisar no GitHub", v -> searchGitHub());
        addButton("Pesquisar no Reddit", v -> searchReddit());

        addLabel("MÓDULOS");

        addInfo("Email Intelligence",
                "Pesquisa referências públicas a um endereço de email.");
        addInfo("Username Intelligence",
                "Procura perfis públicos associados a um nome de utilizador.");
        addInfo("Domain Intelligence",
                "Consulta informações públicas sobre domínios.");
        addInfo("Relatórios",
                "Organiza os resultados para análise posterior.");

        TextView footer = new TextView(this);
        footer.setText("OSINTigator43 • Versão 1.0\nOs resultados externos têm de ser verificados.");
        footer.setTextColor(muted);
        footer.setTextSize(11);
        footer.setPadding(0, 28, 0, 0);
        content.addView(footer);
    }

    private void addLabel(String text) {
        TextView label = new TextView(this);
        label.setText(text);
        label.setTextColor(blue);
        label.setTextSize(12);
        label.setTypeface(null, Typeface.BOLD);
        label.setPadding(0, 8, 0, 12);
        content.addView(label);
    }

    private void addButton(String text, View.OnClickListener action) {
        Button button = new Button(this);
        button.setText(text);
        button.setTextColor(white);
        button.setAllCaps(false);
        button.setTextSize(14);
        button.setBackground(makeBackground(
                Color.rgb(35, 105, 160), 12));
        content.addView(button, params(-1, -2, 0, 0, 0, 10));
        button.setOnClickListener(action);
    }

    private void addInfo(String heading, String description) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(16, 15, 16, 15);
        card.setBackground(makeBackground(panel, 12));

        TextView h = new TextView(this);
        h.setText(heading);
        h.setTextColor(white);
        h.setTextSize(16);
        h.setTypeface(null, Typeface.BOLD);
        card.addView(h);

        TextView d = new TextView(this);
        d.setText(description);
        d.setTextColor(muted);
        d.setTextSize(13);
        d.setPadding(0, 6, 0, 0);
        card.addView(d);

        content.addView(card, params(-1, -2, 0, 0, 0, 10));
    }

    private void searchEmail() {
        String value = searchInput.getText().toString().trim();

        if (!value.contains("@") || value.startsWith("@")
                || value.endsWith("@")) {
            showMessage("Introduz um endereço de email válido.");
            return;
        }

        openSearch("\"" + value + "\"");
    }

    private void searchUsername() {
        String value = searchInput.getText().toString().trim();

        if (value.isEmpty()) {
            showMessage("Introduz um username primeiro.");
            return;
        }

        openSearch("\"" + value + "\" profile");
    }

    private void searchGitHub() {
        String value = searchInput.getText().toString().trim();

        if (value.isEmpty()) {
            showMessage("Introduz um email ou username primeiro.");
            return;
        }

        openUrl("https://github.com/search?q="
                + Uri.encode(value) + "&type=users");
    }

    private void searchReddit() {
        String value = searchInput.getText().toString().trim();

        if (value.isEmpty()) {
            showMessage("Introduz um termo de pesquisa primeiro.");
            return;
        }

        openUrl("https://www.reddit.com/search/?q="
                + Uri.encode(value));
    }

    private void openSearch(String query) {
        openUrl("https://www.google.com/search?q="
                + Uri.encode(query));
    }

    private void openUrl(String url) {
        try {
            Intent intent = new Intent(
                    Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            showMessage("Não foi possível abrir a pesquisa.");
        }
    }

    private void showMessage(String message) {
        android.widget.Toast.makeText(
                this, message, android.widget.Toast.LENGTH_SHORT).show();
    }

    private GradientDrawable makeBackground(int color, int radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(radius * getResources()
                .getDisplayMetrics().density);
        return drawable;
    }

    private LinearLayout.LayoutParams params(
            int width, int height,
            int left, int top, int right, int bottom) {
        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(width, height);
        p.setMargins(left, top, right, bottom);
        return p;
    }
}
