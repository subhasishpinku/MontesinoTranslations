package montesino.translation.montesinotranslation.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import montesino.translation.montesinotranslation.R;
import montesino.translation.montesinotranslation.api.ApiClient;
import montesino.translation.montesinotranslation.global.Global;
import montesino.translation.montesinotranslation.model.CardModel;
import montesino.translation.montesinotranslation.response.AddCardResponse;
import montesino.translation.montesinotranslation.response.CardListResponse;
import montesino.translation.montesinotranslation.response.CardRemoveResponse;
import montesino.translation.montesinotranslation.response.UpdateCardResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreditDebitFragment extends Fragment implements OnBackClickListener{
    private BackButtonHandlerInterface backButtonHandler;
    boolean doubleBackToExitPressedOnce = false;
    RecyclerView cardsRecyclerView;
    EditText searchBy;
    LinearLayout add_cards, reload;
    SharedPreferences shared;
    String userId, account_no;
    Global global;
    CardListAdapter cardAdapter;
    List<CardModel> cardModel = new ArrayList<>();
    BottomSheetDialog sheetDialog;
    EditText holderName, holderNickName, cardNo, expiryMonth, expiryYear;
    TextView cardNumber, card_holder_name, expiry_date, card_label;
    String chooseYear = "",choosesMonth = "", card_issue_type;
    ImageView card_type;
    LinearLayout card_bg, saveCard, cancelCard;
    private static final String EMPTY_STRING = "";
    private static final String WHITE_SPACE = " ";
    private String lastSource = EMPTY_STRING;
    BottomSheetBehavior bottomSheetBehavior;
    String cardId = "";
    RelativeLayout credit_debit;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        backButtonHandler = (BackButtonHandlerInterface) activity;
        backButtonHandler.addBackClickListener(this);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.credit_debit_fragment, container, false);
        global = new Global();
        initialize(rootView);
        return rootView;
    }

    private void initialize(View root) {
        cardsRecyclerView = root.findViewById(R.id.cardsRecyclerView);
        searchBy = root.findViewById(R.id.searchBy);
        add_cards = root.findViewById(R.id.add_cards);
        reload = root.findViewById(R.id.reload);
        credit_debit  = root.findViewById(R.id.credit_debit);
        shared = getActivity().getSharedPreferences("montesino_translation", MODE_PRIVATE);
        userId = shared.getString("user_id", "");
        account_no = shared.getString("account_no", "");
        if(global.isNetworkAvailable(getContext())) {
            getCardList();
        } else {
            Toast.makeText(getContext(), getResources().getString(R.string.network_check), Toast.LENGTH_SHORT).show();
        }

        reload.setOnClickListener(v -> {
            if(global.isNetworkAvailable(getContext())) {
                getCardList();
            } else {
                Toast.makeText(getContext(), getResources().getString(R.string.network_check), Toast.LENGTH_SHORT).show();
            }
        });

        searchBy.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(cardModel.size() > 0) {
                    cardAdapter.getFilter().filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        add_cards.setOnClickListener(v -> {
            openCard("Add", "", "", "", "", "", "");
        });
    }

    @SuppressLint("MissingInflatedId")
    private void openCard(String mode, String cardHolderName, String nickName, String enCardNumber, String expiry, String cardType, String card_id) {
        sheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetStyle);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View card = inflater.inflate(R.layout.add_edit_card, null);

        holderName = card.findViewById(R.id.holderName);
        holderNickName = card.findViewById(R.id.holderNickName);
        cardNo = card.findViewById(R.id.cardNo);
        expiryMonth = card.findViewById(R.id.expiryMonth);
        expiryYear = card.findViewById(R.id.expiryYear);
        cardNumber = card.findViewById(R.id.cardNumber);
        card_holder_name = card.findViewById(R.id.card_holder_name);
        expiry_date = card.findViewById(R.id.expiry_date);
        card_type = card.findViewById(R.id.card_type);
        card_bg = card.findViewById(R.id.card_bg);
        saveCard = card.findViewById(R.id.saveCard);
        cancelCard = card.findViewById(R.id.cancelCard);
        card_label = card.findViewById(R.id.card_label);

        expiryMonth.setOnClickListener(v -> {
            selectMonth();
        });
        expiryYear.setOnClickListener(v->{
            selectYear();
        });
        holderName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals("")) {
                    card_holder_name.setText(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        cardNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ccNum = s.toString();
                if(ccNum.equals("")) {
                    cardNumber.setText("XXXX XXXX XXXX XXXX");
                } else {
                    cardNumber.setText(ccNum);
                }
                String card_types = getCardType(ccNum);
                card_issue_type = card_types;
                if(card_issue_type.equals("is_visa")) {
                    card_type.setImageDrawable(getResources().getDrawable(R.drawable.logo_visa));
                    GradientDrawable gradientDrawable = new GradientDrawable(
                            GradientDrawable.Orientation.BL_TR,
                            new int[]{0xFF434343, 0xFF000000});
                    LayerDrawable layerDrawable = new LayerDrawable(new android.graphics.drawable.Drawable[]{gradientDrawable, getResources().getDrawable(R.drawable.card_bg)});

                    card_bg.setBackground(layerDrawable);
                } else if(card_issue_type.equals("is_mastercard")) {
                    card_type.setImageDrawable(getResources().getDrawable(R.drawable.logo_mastercard));
                    GradientDrawable gradientDrawable = new GradientDrawable(
                            GradientDrawable.Orientation.BL_TR,
                            new int[]{0xFFED213A, 0xFF93291E});
                    LayerDrawable layerDrawable = new LayerDrawable(new android.graphics.drawable.Drawable[]{gradientDrawable, getResources().getDrawable(R.drawable.card_bg)});

                    card_bg.setBackground(layerDrawable);
                } else if(card_issue_type.equals("is_amex")) {
                    card_type.setImageDrawable(getResources().getDrawable(R.drawable.logo_amex));
                    GradientDrawable gradientDrawable = new GradientDrawable(
                            GradientDrawable.Orientation.BL_TR,
                            new int[]{0xFFE9E4F0, 0xFFD3CCE3});
                    LayerDrawable layerDrawable = new LayerDrawable(new android.graphics.drawable.Drawable[]{gradientDrawable, getResources().getDrawable(R.drawable.card_bg)});

                    card_bg.setBackground(layerDrawable);
                } else if(card_issue_type.equals("is_diners")) {
                    card_type.setImageDrawable(getResources().getDrawable(R.drawable.logo_diners));
                    GradientDrawable gradientDrawable = new GradientDrawable(
                            GradientDrawable.Orientation.BL_TR,
                            new int[]{0xFF237A57, 0xFF093028});
                    LayerDrawable layerDrawable = new LayerDrawable(new android.graphics.drawable.Drawable[]{gradientDrawable, getResources().getDrawable(R.drawable.card_bg)});

                    card_bg.setBackground(layerDrawable);
                } else if(card_issue_type.equals("is_jcb")) {
                    card_type.setImageDrawable(getResources().getDrawable(R.drawable.logo_jcb));
                    GradientDrawable gradientDrawable = new GradientDrawable(
                            GradientDrawable.Orientation.BL_TR,
                            new int[]{0xFF4b6cb7, 0xFF182848});
                    LayerDrawable layerDrawable = new LayerDrawable(new android.graphics.drawable.Drawable[]{gradientDrawable, getResources().getDrawable(R.drawable.card_bg)});

                    card_bg.setBackground(layerDrawable);
                } else if(card_issue_type.equals("is_discover")) {
                    card_type.setImageDrawable(getResources().getDrawable(R.drawable.logo_discover));
                    GradientDrawable gradientDrawable = new GradientDrawable(
                            GradientDrawable.Orientation.BL_TR,
                            new int[]{0xFF0f0c29, 0xFF302b63, 0xFF24243e});
                    LayerDrawable layerDrawable = new LayerDrawable(new android.graphics.drawable.Drawable[]{gradientDrawable, getResources().getDrawable(R.drawable.card_bg)});

                    card_bg.setBackground(layerDrawable);
                } else {
                    card_type.setImageDrawable(getResources().getDrawable(R.drawable.logo_generic));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                makeCardNumber(s);
            }
        });

        saveCard.setOnClickListener(v-> {
            validateCard(mode);
        });

        cancelCard.setOnClickListener(v -> {
            sheetDialog.dismiss();
        });

        if(mode.equals("Edit")) {
            card_label.setText(getResources().getString(R.string.edit_credit_debit));
            cardId = card_id;
            String year = expiry.toString().split("/")[1];
            String month = expiry.toString().split("/")[0];
            choosesMonth = month;
            chooseYear = year;
            holderName.setText(cardHolderName);
            holderNickName.setText(nickName);
            cardNo.setText(enCardNumber);
            expiryMonth.setText(month);
            expiryYear.setText(year);
            card_issue_type = cardType;
            if(card_issue_type.equals("is_visa")) {
                card_type.setImageDrawable(getResources().getDrawable(R.drawable.logo_visa));
                GradientDrawable gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.BL_TR,
                        new int[]{0xFF434343, 0xFF000000});
                LayerDrawable layerDrawable = new LayerDrawable(new android.graphics.drawable.Drawable[]{gradientDrawable, getResources().getDrawable(R.drawable.card_bg)});

                card_bg.setBackground(layerDrawable);
            } else if(card_issue_type.equals("is_mastercard")) {
                card_type.setImageDrawable(getResources().getDrawable(R.drawable.logo_mastercard));
                GradientDrawable gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.BL_TR,
                        new int[]{0xFFED213A, 0xFF93291E});
                LayerDrawable layerDrawable = new LayerDrawable(new android.graphics.drawable.Drawable[]{gradientDrawable, getResources().getDrawable(R.drawable.card_bg)});

                card_bg.setBackground(layerDrawable);
            } else if(card_issue_type.equals("is_amex")) {
                card_type.setImageDrawable(getResources().getDrawable(R.drawable.logo_amex));
                GradientDrawable gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.BL_TR,
                        new int[]{0xFFE9E4F0, 0xFFD3CCE3});
                LayerDrawable layerDrawable = new LayerDrawable(new android.graphics.drawable.Drawable[]{gradientDrawable, getResources().getDrawable(R.drawable.card_bg)});

                card_bg.setBackground(layerDrawable);
            } else if(card_issue_type.equals("is_diners")) {
                card_type.setImageDrawable(getResources().getDrawable(R.drawable.logo_diners));
                GradientDrawable gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.BL_TR,
                        new int[]{0xFF237A57, 0xFF093028});
                LayerDrawable layerDrawable = new LayerDrawable(new android.graphics.drawable.Drawable[]{gradientDrawable, getResources().getDrawable(R.drawable.card_bg)});

                card_bg.setBackground(layerDrawable);
            } else if(card_issue_type.equals("is_jcb")) {
                card_type.setImageDrawable(getResources().getDrawable(R.drawable.logo_jcb));
                GradientDrawable gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.BL_TR,
                        new int[]{0xFF4b6cb7, 0xFF182848});
                LayerDrawable layerDrawable = new LayerDrawable(new android.graphics.drawable.Drawable[]{gradientDrawable, getResources().getDrawable(R.drawable.card_bg)});

                card_bg.setBackground(layerDrawable);
            } else if(card_issue_type.equals("is_discover")) {
                card_type.setImageDrawable(getResources().getDrawable(R.drawable.logo_discover));
                GradientDrawable gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.BL_TR,
                        new int[]{0xFF0f0c29, 0xFF302b63, 0xFF24243e});
                LayerDrawable layerDrawable = new LayerDrawable(new android.graphics.drawable.Drawable[]{gradientDrawable, getResources().getDrawable(R.drawable.card_bg)});

                card_bg.setBackground(layerDrawable);
            } else {
                card_type.setImageDrawable(getResources().getDrawable(R.drawable.logo_generic));
            }
        }

        sheetDialog.setContentView(card);
        bottomSheetBehavior = BottomSheetBehavior.from((View)card.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        sheetDialog.show();
    }

    private void validateCard(String mode) {
        if(mode.equals("Add")) {
            if(card_holder_name.getText().toString().equals("")) {
                card_holder_name.setError(getResources().getString(R.string.card_holder_name_validation));
            } else if (cardNo.getText().toString().equals("")) {
                cardNo.setError(getResources().getString(R.string.card_number_validation));
            } else if (choosesMonth.equals("")) {
                expiryMonth.setError(getResources().getString(R.string.card_expiry_month));
                expiryMonth.setFocusable(true);
                expiryMonth.setFocusableInTouchMode(true);
                expiryMonth.requestFocus();
            } else if (chooseYear.equals("")) {
                expiryYear.setError(getResources().getString(R.string.card_expiry_year));
                expiryYear.setFocusable(true);
                expiryYear.setFocusableInTouchMode(true);
                expiryYear.requestFocus();
            } else {
                saveNewCard();
            }
        } else {
            if(card_holder_name.getText().toString().equals("")) {
                card_holder_name.setError(getResources().getString(R.string.card_holder_name_validation));
            } else if (cardNo.getText().toString().equals("")) {
                cardNo.setError(getResources().getString(R.string.card_number_validation));
            } else if (choosesMonth.equals("")) {
                expiryMonth.setError(getResources().getString(R.string.card_expiry_month));
                expiryMonth.setFocusable(true);
                expiryMonth.setFocusableInTouchMode(true);
                expiryMonth.requestFocus();
            } else if (chooseYear.equals("")) {
                expiryYear.setError(getResources().getString(R.string.card_expiry_year));
                expiryYear.setFocusable(true);
                expiryYear.setFocusableInTouchMode(true);
                expiryYear.requestFocus();
            } else if (cardNo.getText().toString().contains("X")) {
                cardNo.setError(getResources().getString(R.string.card_re_number_validation));
                cardNo.requestFocus();
            } else {
                updateCard();
            }
        }
    }

    private void updateCard() {
        Call<UpdateCardResponse> saveCard = ApiClient.getInstance().getMontesinoApi().updateCard(
                userId, account_no,
                card_holder_name.getText().toString(),
                holderNickName.getText().toString(),
                cardNo.getText().toString().replace(" ", ""),
                choosesMonth, chooseYear, cardId, card_issue_type);
        saveCard.enqueue(new Callback<UpdateCardResponse>() {
            @Override
            public void onResponse(Call<UpdateCardResponse> call, Response<UpdateCardResponse> response) {
                if(response.body().getStatusCode() == 200) {
                    Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    sheetDialog.dismiss();
                    getCardList();
                } else {
                    Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateCardResponse> call, Throwable t) {

            }
        });
    }

    private void saveNewCard() {
        Call<AddCardResponse> saveCard = ApiClient.getInstance().getMontesinoApi().addCard(
                userId, account_no,
                card_holder_name.getText().toString(),
                holderNickName.getText().toString(),
                cardNo.getText().toString().replace(" ", ""),
                choosesMonth, chooseYear, card_issue_type);
        saveCard.enqueue(new Callback<AddCardResponse>() {
            @Override
            public void onResponse(Call<AddCardResponse> call, Response<AddCardResponse> response) {
                if(response.body().getStatusCode() == 200) {
                    Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    sheetDialog.dismiss();
                    getCardList();
                } else {
                    Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCardResponse> call, Throwable t) {

            }
        });
    }

    private void makeCardNumber(Editable s) {
        String source = s.toString();
        if (!lastSource.equals(source)) {
            source = source.replace(WHITE_SPACE, EMPTY_STRING);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i > 0 && i % 4 == 0) {
                    stringBuilder.append(WHITE_SPACE);
                }
                stringBuilder.append(source.charAt(i));
            }
            lastSource = stringBuilder.toString();
            s.replace(0, s.length(), lastSource);
        }
    }

    public static String getCardType(String getCardNumber) {
        String cardNumber = getCardNumber.replace(" ", "");
        // Define regular expressions for each card type
        String mastercardPattern = "^5[1-5][0-9]{14}$";
        String visaPattern = "^4[0-9]{12}(?:[0-9]{3})?$";
        String amexPattern = "^3[47][0-9]{13}$";
        String discoverPattern = "^6(?:011|5[0-9]{2})[0-9]{12}$";
        String jcbPattern = "^(?:2131|1800|35\\d{3})\\d{11}$";
        String dinersPattern = "^3(?:0[0-5]|[68][0-9])?[0-9]{11}$";

        // Check the card number against each pattern
        if (cardNumber.matches(mastercardPattern)) {
            return "is_mastercard";
        } else if (cardNumber.matches(visaPattern)) {
            return "is_visa";
        } else if (cardNumber.matches(amexPattern)) {
            return "is_amex";
        } else if (cardNumber.matches(discoverPattern)) {
            return "is_discover";
        } else if (cardNumber.matches(jcbPattern)) {
            return "is_jcb";
        } else if (cardNumber.matches(dinersPattern)) {
            return "is_diners";
        } else {
            return "";
        }
    }

    private void selectYear() {
        final Calendar today = Calendar.getInstance();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(getContext(), new MonthPickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(int selectedMonth, int selectedYear) {
                expiryYear.setText(chooseYear);
                if(choosesMonth.equals("")) {
                    expiry_date.setText("XX/"+chooseYear);
                } else {
                    boolean check = checkExpiry(choosesMonth+"/"+chooseYear);
                    if(check) {
                        expiry_date.setText(choosesMonth + "/" + chooseYear);
                    } else {
                        Toast.makeText(getContext(), getResources().getString(R.string.card_already_expired), Toast.LENGTH_SHORT).show();
                        expiryMonth.setText("");
                        expiryYear.setText("");
                        choosesMonth = "";
                        chooseYear = "";
                        expiry_date.setText("XX/XXXX");
                    }
                }
            }
        }, today.get(Calendar.YEAR), today.get(Calendar.MONTH));
        builder.showYearOnly()
                .setMinYear(currentYear)
                .setActivatedYear(Calendar.getInstance().get(Calendar.YEAR))
                .setMaxYear(currentYear + 10)
                .setOnYearChangedListener(new MonthPickerDialog.OnYearChangedListener() {
                    @Override
                    public void onYearChanged(int selectedYear) {
                        System.out.println("Print"+"selectedYear "+selectedYear);
                        chooseYear = String.valueOf(selectedYear);
                    }
                })
                .build()
                .show();
    }

    private void selectMonth() {
        final Calendar today = Calendar.getInstance();
        MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(getContext(), new MonthPickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(int selectedMonth, int selectedYear) {
                expiryMonth.setText(choosesMonth);
                if(chooseYear.equals("")) {
                    expiry_date.setText(choosesMonth+"/XXXX");
                } else {
                    boolean check = checkExpiry(choosesMonth+"/"+chooseYear);
                    if(check) {
                        expiry_date.setText(choosesMonth + "/" + chooseYear);
                    } else {
                        Toast.makeText(getContext(), getResources().getString(R.string.card_already_expired), Toast.LENGTH_SHORT).show();
                        expiryMonth.setText("");
                        expiryYear.setText("");
                        choosesMonth = "";
                        chooseYear = "";
                        expiry_date.setText("XX/XXXX");
                    }
                }
            }
        }, today.get(Calendar.YEAR), today.get(Calendar.MONTH));

        builder.showMonthOnly()
                //.setTitle(getResources().getString(R.string.select_to_upload_pnl))
                .setMonthRange(Calendar.JANUARY, Calendar.DECEMBER)
                .setOnMonthChangedListener(new MonthPickerDialog.OnMonthChangedListener() {
                    @Override
                    public void onMonthChanged(int selectedMonth) {

                        if((selectedMonth+1) <= 9) {
                            choosesMonth = "0"+(selectedMonth+1);
                        } else {
                            choosesMonth = String.valueOf((selectedMonth+1));
                        }
                        System.out.println("Print"+"selectedMonth "+choosesMonth);

                    }
                })
                .build()
                .show();
    }

    private void getCardList() {
        cardModel.clear();
        Call<CardListResponse> cardList = ApiClient.getInstance().getMontesinoApi().cardList(userId, account_no);
        cardList.enqueue(new Callback<CardListResponse>() {
            @Override
            public void onResponse(Call<CardListResponse> call, Response<CardListResponse> response) {
                if(response.body().getStatusCode() == 200) {
                    for(int i = 0; i < response.body().getData().size(); i++) {
                        cardModel.add(new CardModel(
                                response.body().getData().get(i).getId(),
                                response.body().getData().get(i).getCardHolder(),
                                response.body().getData().get(i).getCardHolderNickname(),
                                response.body().getData().get(i).getExpiryDate(),
                                response.body().getData().get(i).getCardType(),
                                response.body().getData().get(i).getCardNumber(),
                                response.body().getData().get(i).getFullCardNumber(),
                                response.body().getData().get(i).getUserId(),
                                response.body().getData().get(i).getAccountNo()
                        ));
                    }

                    if(cardModel.size() > 0) {
                        credit_debit.setVisibility(View.GONE);
                        cardAdapter = new CardListAdapter(cardModel, getContext());
                        cardsRecyclerView.setAdapter(cardAdapter);
                        cardsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        cardAdapter.notifyDataSetChanged();
                    }else {
                        credit_debit.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CardListResponse> call, Throwable t) {

            }
        });
    }

    private boolean checkExpiry(String expiry) {
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat yearFormat = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            yearFormat = new SimpleDateFormat("YYYY");
        }
        String currentYear = yearFormat.format(new Date());
        String currentMonth = monthFormat.format(new Date());
        String year = expiry.toString().split("/")[1];
        String month = expiry.toString().split("/")[0];
        if(Integer.parseInt(month) < Integer.parseInt(currentMonth) && Integer.parseInt(year) == Integer.parseInt(currentYear)) {
            return false;
        } else if (Integer.parseInt(month) >= Integer.parseInt(currentMonth) && Integer.parseInt(year) == Integer.parseInt(currentYear)) {
            return true;
        } else if (Integer.parseInt(year) >= Integer.parseInt(currentYear) && Integer.parseInt(month) >= Integer.parseInt(currentMonth)) {
            return true;
        } else if (Integer.parseInt(year) >= Integer.parseInt(currentYear) && Integer.parseInt(month) <= Integer.parseInt(currentMonth)) {
            return true;
        } else if (Integer.parseInt(month) >= Integer.parseInt(currentMonth) && Integer.parseInt(year) < Integer.parseInt(currentYear)) {
            return false;
        } else {
            return false;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        backButtonHandler.removeBackClickListener(this);
        backButtonHandler = null;
    }

    @SuppressLint("NewApi")
    @Override
    public boolean onBackClick() {
        if (doubleBackToExitPressedOnce) {
            // super.onBackPressed();
            getActivity().finishAffinity();
            System.exit(0);
            return false;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(getContext(), "Please again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;

            }
        }, 2000);
        return true;
    }
    public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.ViewHolder> {
        List<CardModel> cardModel;
        Context context;
        Global global;
        List<CardModel> filterCardList;
        public CardListAdapter(List<CardModel> cardModel, Context context) {
            this.cardModel = cardModel;
            this.context = context;
            global = new Global();
            this.filterCardList = cardModel;
        }
        @NonNull
        @Override
        public CardListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_card, parent, false);
            return new ViewHolder(v);
        }
        @Override
        public void onBindViewHolder(@NonNull CardListAdapter.ViewHolder holder, int position) {
            String alterCardNumber = "";
            if(!cardModel.get(position).getEnCardNumber().equals("")) {
                alterCardNumber = formatString(cardModel.get(position).getEnCardNumber());
            }
            holder.cardNumber.setText(alterCardNumber);
            holder.card_holder_name.setText(cardModel.get(position).getCardHolderName());
            holder.expiry_date.setText(cardModel.get(position).getExpiryDate());
            if(cardModel.get(position).getCardType().equals("is_visa")) {
                holder.card_type.setImageDrawable(context.getResources().getDrawable(R.drawable.logo_visa));
                GradientDrawable gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.BL_TR,
                        new int[]{0xFF434343, 0xFF000000});
                LayerDrawable layerDrawable = new LayerDrawable(new android.graphics.drawable.Drawable[]{gradientDrawable, context.getResources().getDrawable(R.drawable.card_bg)});

                holder.card_bg.setBackground(layerDrawable);
            } else if(cardModel.get(position).getCardType().equals("is_mastercard")) {
                holder.card_type.setImageDrawable(context.getResources().getDrawable(R.drawable.logo_mastercard));
                GradientDrawable gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.BL_TR,
                        new int[]{0xFFED213A, 0xFF93291E});
                LayerDrawable layerDrawable = new LayerDrawable(new android.graphics.drawable.Drawable[]{gradientDrawable, context.getResources().getDrawable(R.drawable.card_bg)});

                holder.card_bg.setBackground(layerDrawable);
            } else if(cardModel.get(position).getCardType().equals("is_amex")) {
                holder.card_type.setImageDrawable(context.getResources().getDrawable(R.drawable.logo_amex));
                GradientDrawable gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.BL_TR,
                        new int[]{0xFFE9E4F0, 0xFFD3CCE3});
                LayerDrawable layerDrawable = new LayerDrawable(new android.graphics.drawable.Drawable[]{gradientDrawable, context.getResources().getDrawable(R.drawable.card_bg)});

                holder.card_bg.setBackground(layerDrawable);
            } else if(cardModel.get(position).getCardType().equals("is_diners")) {
                holder.card_type.setImageDrawable(context.getResources().getDrawable(R.drawable.logo_diners));
                GradientDrawable gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.BL_TR,
                        new int[]{0xFF237A57, 0xFF093028});
                LayerDrawable layerDrawable = new LayerDrawable(new android.graphics.drawable.Drawable[]{gradientDrawable, context.getResources().getDrawable(R.drawable.card_bg)});

                holder.card_bg.setBackground(layerDrawable);
            } else if(cardModel.get(position).getCardType().equals("is_jcb")) {
                holder.card_type.setImageDrawable(context.getResources().getDrawable(R.drawable.logo_jcb));
                GradientDrawable gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.BL_TR,
                        new int[]{0xFF4b6cb7, 0xFF182848});
                LayerDrawable layerDrawable = new LayerDrawable(new android.graphics.drawable.Drawable[]{gradientDrawable, context.getResources().getDrawable(R.drawable.card_bg)});

                holder.card_bg.setBackground(layerDrawable);
            } else if(cardModel.get(position).getCardType().equals("is_discover")) {
                holder.card_type.setImageDrawable(context.getResources().getDrawable(R.drawable.logo_discover));
                GradientDrawable gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.BL_TR,
                        new int[]{0xFF0f0c29, 0xFF302b63, 0xFF24243e});
                LayerDrawable layerDrawable = new LayerDrawable(new android.graphics.drawable.Drawable[]{gradientDrawable, context.getResources().getDrawable(R.drawable.card_bg)});

                holder.card_bg.setBackground(layerDrawable);
            } else {
                holder.card_type.setImageDrawable(context.getResources().getDrawable(R.drawable.logo_generic));
            }
            holder.remove_card.setOnClickListener(v -> {
                sendAlert(position);
            });

            holder.clickEdit.setOnClickListener(v -> {
                openCard("Edit",
                        cardModel.get(position).getCardHolderName(),
                        cardModel.get(position).getCardHolderNickname(),
                        cardModel.get(position).getEnCardNumber(),
                        cardModel.get(position).getExpiryDate(),
                        cardModel.get(position).getCardType(),
                        cardModel.get(position).getCardId());
            });
        }
        @Override
        public int getItemCount() {
            return cardModel.size();
        }
        private void sendAlert(int position) {
            if(global.isNetworkAvailable(context)) {
                AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                builder.setMessage(context.getResources().getString(R.string.remove_card));
                builder.setPositiveButton(context.getResources().getString(R.string.no), (dialog, which) -> { dialog.dismiss(); });
                builder.setNegativeButton(context.getResources().getString(R.string.yes), (dialogInterface, i) -> removeCard(cardModel.get(position).getCardId(), cardModel.get(position).getUserId(), cardModel.get(position).getAccountNo()));
                android.app.AlertDialog alert = builder.create();
                alert.show();
            } else {
                Toast.makeText(context, context.getResources().getString(R.string.please_Check_Your_Internet_Connection), Toast.LENGTH_SHORT).show();
            }
        }

        private void removeCard(String cardId, String userId, String accountNo) {
            Call<CardRemoveResponse> cardRemove = ApiClient.getInstance().getMontesinoApi().cardRemove(cardId, userId, accountNo);
            cardRemove.enqueue(new Callback<CardRemoveResponse>() {
                @Override
                public void onResponse(Call<CardRemoveResponse> call, Response<CardRemoveResponse> response) {
                    if(response.body().getStatusCode() == 200) {
                        Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        getCardList();
                    } else {
                        Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CardRemoveResponse> call, Throwable t) {

                }
            });
        }


        private String formatString(String originalString) {
            StringBuilder formattedString = new StringBuilder();

            // Add spaces in the desired pattern
            for (int i = 0; i < originalString.length(); i++) {
                if (i > 0 && i % 4 == 0) {
                    formattedString.append(" "); // Add a space every 4 characters
                }
                formattedString.append(originalString.charAt(i));
            }

            return formattedString.toString();
        }

        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    FilterResults filterResults = new FilterResults();
                    if(charSequence == null || charSequence.length() == 0) {
                        filterResults.values = filterCardList;
                        filterResults.count = filterCardList.size();
                    } else {
                        String search = charSequence.toString().toLowerCase();
                        List<CardModel> cList = new ArrayList<>();
                        for(CardModel cFilterList: filterCardList) {
                            if(cFilterList.getCardHolderName().toLowerCase().contains(search) || cFilterList.getFullCardNumber().toLowerCase().contains(search) || cFilterList.getExpiryDate().toLowerCase().contains(search)) {
                                cList.add(cFilterList);
                            }
                        }
                        filterResults.values = cList;
                        filterResults.count = cList.size();
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    cardModel = (List<CardModel>)results.values;
                    notifyDataSetChanged();
                }
            };
            return filter;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView cardNumber, card_holder_name, expiry_date;
            ImageView card_type;
            LinearLayout card_bg, remove_card;
            CardView clickEdit;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                cardNumber = (itemView).findViewById(R.id.cardNumber);
                card_holder_name = (itemView).findViewById(R.id.card_holder_name);
                expiry_date = (itemView).findViewById(R.id.expiry_date);
                card_type = (itemView).findViewById(R.id.card_type);
                card_bg = (itemView).findViewById(R.id.card_bg);
                remove_card = (itemView).findViewById(R.id.remove_card);
                clickEdit = (itemView).findViewById(R.id.clickEdit);
            }
        }
    }
}
