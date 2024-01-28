package montesino.translation.montesinotranslation.activity;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.CompoundButtonCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.angads25.toggle.model.ToggleableView;
import com.github.angads25.toggle.widget.LabeledSwitch;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import montesino.translation.montesinotranslation.R;
import montesino.translation.montesinotranslation.api.ApiClient;
import montesino.translation.montesinotranslation.fragment.BillingAddressFragment;
import montesino.translation.montesinotranslation.fragment.CreditDebitFragment;
import montesino.translation.montesinotranslation.fragment.ShippingAddressFragment;
import montesino.translation.montesinotranslation.fragment.ShippingAddressPaymentModel;
import montesino.translation.montesinotranslation.global.Global;
import montesino.translation.montesinotranslation.model.BillingAddressModel;
import montesino.translation.montesinotranslation.model.BillingAddressPaymentMode;
import montesino.translation.montesinotranslation.model.CardModel;
import montesino.translation.montesinotranslation.model.CountryModel;
import montesino.translation.montesinotranslation.model.CreditDebitCardsModel;
import montesino.translation.montesinotranslation.model.NotarizeAmountResponse;
import montesino.translation.montesinotranslation.model.ShippingModel;
import montesino.translation.montesinotranslation.model.StateModel;
import montesino.translation.montesinotranslation.model.ValidateCouponResponse;
import montesino.translation.montesinotranslation.model.ValidateGiftCardResponse;
import montesino.translation.montesinotranslation.response.AddCardResponse;
import montesino.translation.montesinotranslation.response.BillingAddressResponse;
import montesino.translation.montesinotranslation.response.BillingAddressSaveResponse;
import montesino.translation.montesinotranslation.response.CardListResponse;
import montesino.translation.montesinotranslation.response.CountryResponse;
import montesino.translation.montesinotranslation.response.PersonalInfoResponse;
import montesino.translation.montesinotranslation.response.ShippingAddressSaveResponse;
import montesino.translation.montesinotranslation.response.ShippingResponse;
import montesino.translation.montesinotranslation.response.UpdateCardResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentOptionActivity extends AppCompatActivity {
    RecyclerView card_Debit_RecyclerView,select_billing_address_RecyclerView,select_shipping_address_RecyclerView;
    CreditDebitCardsAdapter creditDebitCardsAdapter;
    List<CreditDebitCardsModel> creditDebitCardsModels;
    List<BillingAddressPaymentMode> billingAddressPaymentModes;
    List<ShippingAddressPaymentModel> shippingAddressPaymentModels;
    CardView lv_minusPlus_Ids;
    RelativeLayout minusPlus_Ids;
    TextView tv_add,tv_add_ext;
    ImageView minus,plus;
    int minusInteger = 0;
    SwitchMaterial make_amount;
    TextInputLayout amount;
    LabeledSwitch switch_payment;
    private SwitchCompat switchOnOff;
    private TextView tvSwitchPaypal;
    private TextView tvSwitchCredit;
    CardView card_credit_debit;
    ImageView credit_add_Id,add_Id_billing,add_Id_shipping,close;
    BottomSheetDialog sheetDialog;
    TextInputEditText countryIds,address1s,city,states,zipCodes,address2s;
    TextInputLayout address1,address2,state,state1;
    LinearLayout saveId,resetID;
    EditText states1,searchBy;
    Global global;
    Dialog langDialog;
    RecyclerView billingAddressRecyclerView,country_list,state_list;
    CountryAdapter countryAdapter;
    List<CountryModel> countryModel = new ArrayList<>();
    String selectCountryName,selectCountryId,selectStateName,selectStateId;
    String userId, account_no,email;
    SharedPreferences shared;
    List<StateModel> stateModel = new ArrayList<>();
    StateAdapter stateAdapter;
    CheckBox checkBox;
    LinearLayout saveId_billing;
    boolean selectCheckBox= false;
    String selectCountryValue = "";
    CardView card_billing_address;
    CardView select_shipping_address;
    EditText holderName, holderNickName, cardNo, expiryMonth, expiryYear;
    TextView cardNumber, card_holder_name, expiry_date, card_label;
    ImageView card_type;
    LinearLayout card_bg, saveCard, cancelCard;
    String chooseYear = "",choosesMonth = "", card_issue_type;
    private static final String EMPTY_STRING = "";
    private static final String WHITE_SPACE = " ";
    private String lastSource = EMPTY_STRING;
    String cardId = "";
    BottomSheetBehavior bottomSheetBehavior;
    String amounts;
    TextView tv_translation_dollars;
    String translationEarned;
    String translationEarned_s;
    TextView totalAmount,dollar_amount,notarized_amount,use_coupon_amount;
    RadioButton gift_card_yes,gift_card_no,coupon_yes,coupon_no;
    EditText gift_amount,coupon_amount;
    RadioGroup radioGroup_gift_card,radioGroup_coupon;
    TextView g_Validate,coupon_Validate,pro_amount,t_dollar_use,gft_code;
    TextInputEditText  amountIds;
    Double translationDollars;
    String formattedResult;
    ProgressBar pro_Validate,pro_coupon_Validate;
    LinearLayout lv_gift,lv_gift_pro,lv_coupon_Validate,lv_pro_coupon_Validate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_option_activity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        android.graphics.drawable.Drawable background = PaymentOptionActivity.this.getDrawable(R.drawable.screen_header);
        getWindow().setBackgroundDrawable(background);
        card_Debit_RecyclerView = findViewById(R.id.card_Debit_RecyclerView);
        select_billing_address_RecyclerView = findViewById(R.id.select_billing_address_RecyclerView);
        select_shipping_address_RecyclerView = findViewById(R.id.select_shipping_address_RecyclerView);
        lv_minusPlus_Ids  = findViewById(R.id.lv_minusPlus_Ids);
        minusPlus_Ids = findViewById(R.id.minusPlus_Ids);
        make_amount = findViewById(R.id.make_amount);
        amount = findViewById(R.id.amount);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        tv_add_ext = findViewById(R.id.tv_add_ext);
        tv_add = findViewById(R.id.tv_add);
        switch_payment = findViewById(R.id.switch_payment);
        switchOnOff = findViewById(R.id.switchOnOff);
        tvSwitchPaypal = findViewById(R.id.tvSwitchPaypal);
        tvSwitchCredit = findViewById(R.id.tvSwitchCredit);
        switchOnOff= findViewById(R.id.switchOnOff);
        card_credit_debit = findViewById(R.id.card_credit_debit);
        credit_add_Id = findViewById(R.id.credit_add_Id);
        add_Id_billing = findViewById(R.id.add_Id_billing);
        add_Id_shipping = findViewById(R.id.add_Id_shipping);
        saveId_billing = findViewById(R.id.saveId_billing);
        checkBox = findViewById(R.id.checkBox);
        card_billing_address = findViewById(R.id.card_billing_address);
        select_shipping_address = findViewById(R.id.select_shipping_address);
        tv_translation_dollars = findViewById(R.id.tv_translation_dollars);
        totalAmount = findViewById(R.id.totalAmount);
        dollar_amount = findViewById(R.id.dollar_amount);
        notarized_amount = findViewById(R.id.notarized_amount);
        radioGroup_gift_card = (RadioGroup) findViewById(R.id.radioGroup_gift_card);
        gift_card_yes = findViewById(R.id.gift_card_yes);
        gift_card_no = findViewById(R.id.gift_card_no);
        g_Validate = findViewById(R.id.g_Validate);
        //
        radioGroup_coupon = findViewById(R.id.radioGroup_coupon);
        coupon_yes = findViewById(R.id.coupon_yes);
        coupon_no = findViewById(R.id.coupon_no);
        lv_gift = findViewById(R.id.lv_gift);
        lv_gift_pro = findViewById(R.id.lv_gift_pro);
        lv_coupon_Validate = findViewById(R.id.lv_coupon_Validate);
        lv_pro_coupon_Validate = findViewById(R.id.lv_pro_coupon_Validate);

        gift_amount = findViewById(R.id.gift_amount);
        coupon_Validate = findViewById(R.id.coupon_Validate);
        pro_coupon_Validate = findViewById(R.id.pro_coupon_Validate);
        coupon_amount = findViewById(R.id.coupon_amount);
        amountIds = findViewById(R.id.amountIds);
        pro_amount = findViewById(R.id.pro_amount);
        t_dollar_use = findViewById(R.id.t_dollar_use);
        use_coupon_amount = findViewById(R.id.use_coupon_amount);
        pro_Validate = findViewById(R.id.pro_Validate);
        gft_code = findViewById(R.id.gft_code);
        checkBox.setVisibility(View.GONE);
        amounts = getIntent().getStringExtra("amount");
        totalAmount.setText(amounts);
        dollar_amount.setText("$"+amounts);
        notarized_amount.setText("$0.00");
        shared = getApplication().getSharedPreferences("montesino_translation", MODE_PRIVATE);
        userId = shared.getString("user_id", "");
        account_no = shared.getString("account_no", "");
        email = shared.getString("email", "");
        fetch_personalInformation(userId,account_no);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the checkbox state change
                if (isChecked) {
                    System.out.println("country"+selectCountryValue);
                    if (selectCountryValue.equals("United States")){
                        select_shipping_address_RecyclerView.setVisibility(View.VISIBLE);
                    }
                } else {
                    select_shipping_address_RecyclerView.setVisibility(View.GONE);
                }
            }
        });
        int thumbColor = ContextCompat.getColor(this, R.color.red);
        switchOnOff.setThumbTintList(ColorStateList.valueOf(thumbColor));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            switchOnOff.setThumbTintList(getColorStateList(R.color.red));
            switchOnOff.setTrackTintList(getColorStateList(R.color.switch_grey));
        } else {
            // For versions prior to Marshmallow
            CompoundButtonCompat.setButtonTintList(switchOnOff,
                    AppCompatResources.getColorStateList(this, R.color.red));
        }
        global = new Global();
        switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Change text color based on the Switch state
                if (isChecked) {
                    // Switch is checked (ON)
                    card_credit_debit.setVisibility(View.VISIBLE);

                } else {
                    // Switch is unchecked (OFF)
                    card_credit_debit.setVisibility(View.GONE);

                }
            }
        });
        switch_payment.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean isOn) {
                System.out.println("checkPayment"+" "+isOn);
                if (isOn==true){

                }else{

                }
            }
        });
        credit_add_Id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCard("Add", "", "", "", "", "", "");
            }
        });
        add_Id_billing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBillingAddress();
            }
        });
        add_Id_shipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShippingAddress();
            }
        });
        fetch_notarizeAmount("0");
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_add_ext.setText("1");
                minusPlus_Ids.setVisibility(View.VISIBLE);
                tv_add.setVisibility(View.GONE);
                fetch_notarizeAmount(tv_add_ext.getText().toString());
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseInteger();
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseInteger();
            }
        });
        amount.setVisibility(View.GONE);
        gift_amount.setEnabled(false);
        g_Validate.setEnabled(false);
        coupon_amount.setEnabled(false);
        coupon_Validate.setEnabled(false);
        pro_amount.setText(getResources().getString(R.string.proceed_with_card)+" "+amounts);
        make_amount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    amount.setVisibility(View.VISIBLE);
                    gift_card_yes.setChecked(false);
                    gift_card_no.setChecked(true);
                    //
                    coupon_yes.setChecked(false);
                    coupon_no.setChecked(true);
                    //
                    gift_amount.setEnabled(false);
                    g_Validate.setEnabled(false);
                    coupon_amount.setEnabled(false);
                    coupon_Validate.setEnabled(false);
                    gift_amount.setText("");
                    pro_amount.setText(getResources().getString(R.string.proceed_with_card)+" "+totalAmount.getText().toString());
                } else {
                    amount.setVisibility(View.GONE);
                    gift_amount.setEnabled(false);
                    g_Validate.setEnabled(false);
                    coupon_amount.setEnabled(false);
                    coupon_Validate.setEnabled(false);
                    amountIds.setText("");
                    coupon_amount.setText("");
                    pro_amount.setText(getResources().getString(R.string.proceed_with_card)+" "+totalAmount.getText().toString());
//                    radioGroup_gift_card.clearCheck();
//                    radioGroup_coupon.clearCheck();
                }
            }
        });
        radioGroup_gift_card.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.gift_card_yes) {
                   gift_amount.setEnabled(true);
                    g_Validate.setEnabled(true);
                    coupon_amount.setText("");
                    pro_amount.setText(getResources().getString(R.string.proceed_with_card)+""+totalAmount.getText().toString());
                    use_coupon_amount.setText("$0.00");

                } else if (checkedId == R.id.gift_card_no) {
                   gift_amount.setEnabled(false);
                    g_Validate.setEnabled(false);
                    coupon_amount.setText("");
                    gift_amount.setText("");
                    pro_amount.setText(getResources().getString(R.string.proceed_with_card)+""+totalAmount.getText().toString());
                    use_coupon_amount.setText("$0.00");
                    gft_code.setText("$0.00");

                }
            }
        });
        gift_card_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup_coupon.clearCheck();
                make_amount.setChecked(false);
                g_Validate.setEnabled(true);
                coupon_Validate.setEnabled(false);

            }
        });
        gift_card_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup_coupon.clearCheck();
                make_amount.setChecked(false);
                g_Validate.setEnabled(false);
                coupon_Validate.setEnabled(false);
                gift_amount.setError(null);
            }
        });
        radioGroup_coupon.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.coupon_yes) {
                    coupon_amount.setEnabled(true);
                    coupon_Validate.setEnabled(true);
                    amountIds.setText("");
                    gift_amount.setText("");
                    pro_amount.setText(getResources().getString(R.string.proceed_with_card)+""+totalAmount.getText().toString());
                    use_coupon_amount.setText("$0.00");
                    gft_code.setText("$0.00");

                } else if (checkedId == R.id.coupon_no) {
                    coupon_amount.setEnabled(false);
                    coupon_Validate.setEnabled(false);
                    amountIds.setText("");
                    gift_amount.setText("");
                    coupon_amount.setText("");
                    pro_amount.setText(getResources().getString(R.string.proceed_with_card)+""+totalAmount.getText().toString());
                    use_coupon_amount.setText("$0.00");
                    gft_code.setText("$0.00");
                }
            }
        });
        coupon_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup_gift_card.clearCheck();
                make_amount.setChecked(false);
                coupon_Validate.setEnabled(true);
                g_Validate.setEnabled(false);
            }
        });
        coupon_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup_gift_card.clearCheck();
                make_amount.setChecked(false);
                coupon_Validate.setEnabled(false);
                g_Validate.setEnabled(false);
            }
        });
        g_Validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gift = gift_amount.getText().toString();
                if (gift.isEmpty()){
                    gift_amount.setError(getResources().getString(R.string.enter_gift_code));
                    gift_amount.requestFocus();
                }else {
                    pro_Validate.setVisibility(View.VISIBLE);
                    lv_gift_pro.setVisibility(View.VISIBLE);
                    lv_gift.setVisibility(View.GONE);
                    fetch_ValidateGiftCard(gift);
                }
            }
        });
        coupon_Validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String couponAmount = coupon_amount.getText().toString();
                if (couponAmount.isEmpty()){
                    coupon_amount.setError(getResources().getString(R.string.enter_coupon_amount));
                    coupon_amount.requestFocus();
                }else {
                    pro_coupon_Validate.setVisibility(View.VISIBLE);
                    lv_coupon_Validate.setVisibility(View.GONE);
                    lv_pro_coupon_Validate.setVisibility(View.VISIBLE);
                    fetch_ValidateCoupon(email,couponAmount,amounts);
                }
            }
        });
        amountIds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count ==0){
                    tv_translation_dollars.setText(translationEarned+" "+translationDollars+" "+translationEarned_s);
                    String translationDollars1 = String.valueOf(translationDollars);
                    t_dollar_use.setText("$"+translationDollars1);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String value = s.toString();
                Double amountsValue = Double.valueOf(amounts);
                if (translationDollars>=amountsValue){
                    try {
                        Double dollarsPurchase = Double.parseDouble(value);
                        if (amountsValue >= dollarsPurchase) {
                            try {
                                Double inputTx = Double.valueOf(amountIds.getText().toString());
                                Double tx_dollar = amountsValue - inputTx;
                                formattedResult = String.format("%.2f", tx_dollar);
                                pro_amount.setText(getResources().getString(R.string.proceed_with_card)+" "+formattedResult);
                                System.out.println("tx_dollar: " + formattedResult);
                                //
                                Double changeTraDollars = translationDollars - inputTx;
                                String changeTraDollarsValue = String.format("%.2f", changeTraDollars);
                                tv_translation_dollars.setText(translationEarned+" "+changeTraDollarsValue+" "+translationEarned_s);
                                t_dollar_use.setText("$"+changeTraDollarsValue);

                            } catch (NumberFormatException e) {
                                System.err.println("Invalid input. Please enter a valid numeric value.");
                                // Handle the exception or provide feedback to the user.
                            }
                        } else {
                            amountIds.setText("");
                            Toast.makeText(PaymentOptionActivity.this,getResources().getString(R.string.payment_amount_from_tx_dollar),Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e) {

                    }
                } else if (amountsValue>=translationDollars) {
                    try {
                        Double dollarsPurchase = Double.parseDouble(value);
                        if (translationDollars >= dollarsPurchase) {
                            try {
                                Double inputTx = Double.valueOf(amountIds.getText().toString());
                                Double tx_dollar = amountsValue - inputTx;
                                formattedResult = String.format("%.2f", tx_dollar);
                                pro_amount.setText(getResources().getString(R.string.proceed_with_card)+" "+formattedResult);
                                System.out.println("tx_dollar: " + formattedResult);
                                //
                                Double changeTraDollars = translationDollars - inputTx;
                                String changeTraDollarsValue = String.format("%.2f", changeTraDollars);
                                tv_translation_dollars.setText(translationEarned+" "+changeTraDollarsValue+" "+translationEarned_s);
                                t_dollar_use.setText("$"+changeTraDollarsValue);

                            } catch (NumberFormatException e) {
                                System.err.println("Invalid input. Please enter a valid numeric value.");
                                // Handle the exception or provide feedback to the user.
                            }
                        } else {
                            amountIds.setText("");
                            Toast.makeText(PaymentOptionActivity.this,getResources().getString(R.string.your_enter_tx_dollar)+" "+"$"+translationDollars,Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e) {

                    }
                }else{

                }

            }
        });
        getCountry();
        getState();
        creditDebitCardsModels = new ArrayList<>();
        billingAddressPaymentModes = new ArrayList<>();
        shippingAddressPaymentModels = new ArrayList<>();
        card_Debit_RecyclerView.setNestedScrollingEnabled(false);
        select_billing_address_RecyclerView.setNestedScrollingEnabled(false);
        select_shipping_address_RecyclerView.setNestedScrollingEnabled(false);
        getBillingAddressList(userId, account_no);
        getShippingList(userId, account_no);
        getCardList();
        saveId_billing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public class CreditDebitCardsAdapter extends RecyclerView.Adapter<CreditDebitCardsAdapter.CreditDebitCardsAdapterViewHolder>{
        private Context mCtx;
        private List<CreditDebitCardsModel> creditDebitCardsModels;
        private int lastCheckedPosition = -1;
        public CreditDebitCardsAdapter(Context mCtx, List<CreditDebitCardsModel> creditDebitCardsModels) {
            this.mCtx = mCtx;
            this.creditDebitCardsModels = creditDebitCardsModels;
        }

        @NonNull
        @Override
        public CreditDebitCardsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.credit_debit_list_adapter, null);
            return new CreditDebitCardsAdapterViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CreditDebitCardsAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
            CreditDebitCardsModel creditDebitCardsModel = creditDebitCardsModels.get(position);
            holder.tv_credit_title.setText(mCtx.getResources().getString(R.string.credit_card)+" "+String.valueOf(position+1));
            holder.tv_credit_subtitle.setText(creditDebitCardsModel.getCardHolderName());
            holder.tv_credit_cardNumber.setText(creditDebitCardsModel.getEnCardNumber());
            holder.tv_credit_date.setText(creditDebitCardsModel.getExpiryDate());
            holder.check.setChecked(creditDebitCardsModel.isChecked());
            holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int adapterPosition = holder.getAdapterPosition(); // Use this to get the position

                    if (isChecked) {
                        // Uncheck the last checked checkbox
                        if (lastCheckedPosition != -1 && lastCheckedPosition != adapterPosition) {
                            creditDebitCardsModels.get(lastCheckedPosition).setChecked(false);
                            notifyItemChanged(lastCheckedPosition);
                        }
                        lastCheckedPosition = adapterPosition;
                    }

                    creditDebitCardsModel.setChecked(isChecked);
                    updateConstraintLayoutBackground(holder);
                }
            });
            holder.color_border.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = holder.getAdapterPosition();
                    boolean newCheckedState = !holder.check.isChecked();
                    holder.check.setChecked(newCheckedState);

                }
            });
        }
        @Override
        public int getItemCount() {
            return creditDebitCardsModels.size();
        }
            private void updateConstraintLayoutBackground(CreditDebitCardsAdapterViewHolder holder) {
                int adapterPosition = holder.getAdapterPosition();

                if (adapterPosition == lastCheckedPosition) {
                    CreditDebitCardsModel lastCheckedModel = creditDebitCardsModels.get(adapterPosition);
                    if (lastCheckedModel.isChecked()) {
                        holder.color_border.setBackgroundResource(R.drawable.add_border);
                    } else {
                        holder.color_border.setBackgroundResource(R.drawable.add_border_white);
                    }
                } else {
                    holder.color_border.setBackgroundResource(R.drawable.add_border_white);
                    System.out.println("Check_Status: " + lastCheckedPosition);
                }
            }


        public class CreditDebitCardsAdapterViewHolder extends RecyclerView.ViewHolder {
            TextView tv_credit_title,tv_credit_subtitle,tv_credit_cardNumber,tv_credit_date;
            RelativeLayout check_layout;
            CheckBox check;
            CardView card_credit_debit;
            ConstraintLayout color_border;
            public CreditDebitCardsAdapterViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_credit_title = itemView.findViewById(R.id.tv_credit_title);
                tv_credit_subtitle = itemView.findViewById(R.id.tv_credit_subtitle);
                tv_credit_cardNumber = itemView.findViewById(R.id.tv_credit_cardNumber);
                tv_credit_date = itemView.findViewById(R.id.tv_credit_date);
                check_layout = itemView.findViewById(R.id.check_layout);
                check = itemView.findViewById(R.id.check);
                card_credit_debit = itemView.findViewById(R.id.card_credit_debit);
                color_border = itemView.findViewById(R.id.color_border);

            }
        }

    }

    private void getBillingAddressList(String userId, String account_no) {
        Call<BillingAddressResponse> jobList = ApiClient.getInstance().getMontesinoApi().billingAddress(userId, account_no);
        jobList.enqueue(new Callback<BillingAddressResponse>() {
            @Override
            public void onResponse(Call<BillingAddressResponse> call, Response<BillingAddressResponse> response) {
                System.out.println("shippingAdd"+response.body().getStatusCode());
                if(response.body().getStatusCode() == 200) {
                    for(int i = 0; i < response.body().getData().size() ; i++) {
                        billingAddressPaymentModes.add(
                                new BillingAddressPaymentMode(
                                        response.body().getData().get(i).getAddress1(),
                                        response.body().getData().get(i).getAddress2(),
                                        response.body().getData().get(i).getCity(),
                                        response.body().getData().get(i).getCountry(),
                                        response.body().getData().get(i).getState(),
                                        response.body().getData().get(i).getZipcode(),
                                        response.body().getData().get(i).getId()));
                    }
                    if(billingAddressPaymentModes.size() > 0) {
                        //billing_address.setVisibility(View.GONE);
                        SelectBillingAddress adapter = new SelectBillingAddress(PaymentOptionActivity.this,billingAddressPaymentModes);
                        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(PaymentOptionActivity.this, 2);
                        select_billing_address_RecyclerView.setLayoutManager(gridLayoutManager1);
                        select_billing_address_RecyclerView.setAdapter(adapter);
                    }else {
                       // billing_address.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<BillingAddressResponse> call, Throwable t) {
                Log.e("RESPONSE CODE",""+t.getMessage());
            }
        });
    }
    public class SelectBillingAddress extends RecyclerView.Adapter<SelectBillingAddress.SelectBillingAddressViewHolder>{
        private Context mCtx;
        private  List<BillingAddressPaymentMode> billingAddressPaymentModes;
        private int lastCheckedPosition = -1;
        public SelectBillingAddress(Context mCtx, List<BillingAddressPaymentMode> billingAddressPaymentModes) {
            this.mCtx = mCtx;
            this.billingAddressPaymentModes = billingAddressPaymentModes;
        }

        @NonNull
        @Override
        public SelectBillingAddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.select_billing_address_list_adapter, null);
            return new SelectBillingAddressViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SelectBillingAddressViewHolder holder, int position) {
            BillingAddressPaymentMode billingAddressPaymentMode = billingAddressPaymentModes.get(position);
            holder.tv_credit_title.setText(mCtx.getResources().getString(R.string.address_title)+" "+String.valueOf(position+1));
            if (!billingAddressPaymentMode.getAddress().equals("")){
                holder.tv_address.setText(billingAddressPaymentMode.getAddressTitle()+","+billingAddressPaymentMode.getAddress()+","+billingAddressPaymentMode.getCity()+","+billingAddressPaymentMode.getCountry()+","+billingAddressPaymentMode.getState()+","+billingAddressPaymentMode.getPinCode());
            }else {
                holder.tv_address.setText(billingAddressPaymentMode.getAddressTitle()+","+billingAddressPaymentMode.getCity()+","+billingAddressPaymentMode.getCountry()+","+billingAddressPaymentMode.getState()+","+billingAddressPaymentMode.getPinCode());
            }
            holder.check.setChecked(billingAddressPaymentMode.isChecked());
            holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int adapterPosition = holder.getAdapterPosition(); // Use this to get the position

                    if (isChecked) {
                        // Uncheck the last checked checkbox
                        if (lastCheckedPosition != -1 && lastCheckedPosition != adapterPosition) {
                            billingAddressPaymentModes.get(lastCheckedPosition).setChecked(false);
                            notifyItemChanged(lastCheckedPosition);
                        }
                        lastCheckedPosition = adapterPosition;
                        checkBox.setVisibility(View.VISIBLE);
                        selectCheckBox = true;
                        selectCountryValue = billingAddressPaymentMode.getCountry();
                        select_shipping_address_RecyclerView.setVisibility(View.VISIBLE);
                        if (selectCountryValue.equals("United States")){
                            select_shipping_address_RecyclerView.setVisibility(View.VISIBLE);
                            checkBox.setVisibility(View.VISIBLE);
                            checkBox.setChecked(false);
                            select_shipping_address.setVisibility(View.VISIBLE);
                        } else {
                        select_shipping_address_RecyclerView.setVisibility(View.GONE);
                            checkBox.setVisibility(View.GONE);
                            select_shipping_address.setVisibility(View.GONE);
                       }
                    }else{
                        checkBox.setVisibility(View.GONE);
                        selectCheckBox = false;
                       // selectCountryValue = "";
                        if (selectCountryValue.equals("United States")){
                            select_shipping_address_RecyclerView.setVisibility(View.VISIBLE);
                            checkBox.setVisibility(View.VISIBLE);
                            select_shipping_address.setVisibility(View.VISIBLE);
                        }
                        else {
                            select_shipping_address_RecyclerView.setVisibility(View.GONE);
                            checkBox.setVisibility(View.GONE);
                            checkBox.setChecked(false);
                            select_shipping_address.setVisibility(View.GONE);
                        }

                    }
                    System.out.println("Select_CountryValue"+" "+selectCountryValue);
                    billingAddressPaymentMode.setChecked(isChecked);
                    updateConstraintLayoutBackground(holder);
                }
            });
            holder.color_border.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = holder.getAdapterPosition();
                    boolean newCheckedState = !holder.check.isChecked();
                    holder.check.setChecked(newCheckedState);
                    System.out.println("Billing_Check"+" "+newCheckedState);
                    if (newCheckedState==true){
                        checkBox.setVisibility(View.VISIBLE);
                        selectCheckBox = true;
                        selectCountryValue = billingAddressPaymentMode.getCountry();
                        if (selectCountryValue.equals("United States")){
                            select_shipping_address_RecyclerView.setVisibility(View.VISIBLE);
                            checkBox.setVisibility(View.VISIBLE);
                            checkBox.setChecked(false);
                            select_shipping_address.setVisibility(View.VISIBLE);
                        }
                        else {
                            select_shipping_address_RecyclerView.setVisibility(View.GONE);
                            checkBox.setVisibility(View.GONE);
                            select_shipping_address.setVisibility(View.GONE);
                        }

                    }else {
                        checkBox.setVisibility(View.GONE);
                        selectCheckBox = false;
                        if (selectCountryValue.equals("United States")){
                            select_shipping_address_RecyclerView.setVisibility(View.VISIBLE);
                            checkBox.setVisibility(View.VISIBLE);
                            select_shipping_address.setVisibility(View.VISIBLE);
                        }
                        else {
                            select_shipping_address_RecyclerView.setVisibility(View.GONE);
                            checkBox.setVisibility(View.GONE);
                            checkBox.setChecked(false);
                            select_shipping_address.setVisibility(View.GONE);
                        }
                    }
                }
            });
        }
        @Override
        public int getItemCount() {
            return billingAddressPaymentModes.size();
        }
        private void updateConstraintLayoutBackground(SelectBillingAddressViewHolder holder) {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition == lastCheckedPosition) {
                BillingAddressPaymentMode lastCheckedModel = billingAddressPaymentModes.get(adapterPosition);
                if (lastCheckedModel.isChecked()) {
                    holder.color_border.setBackgroundResource(R.drawable.add_border);
                    if (selectCountryValue.equals("United States")){
                        select_shipping_address_RecyclerView.setVisibility(View.VISIBLE);
                        checkBox.setVisibility(View.VISIBLE);
                        select_shipping_address.setVisibility(View.VISIBLE);
                    }
                    else {
                        select_shipping_address_RecyclerView.setVisibility(View.GONE);
                        checkBox.setVisibility(View.GONE);
                        checkBox.setChecked(false);
                        select_shipping_address.setVisibility(View.GONE);
                    }
                } else {
                    holder.color_border.setBackgroundResource(R.drawable.add_border_white);
                    selectCountryValue = "";
                    if (selectCountryValue.equals("United States")){
                        select_shipping_address_RecyclerView.setVisibility(View.VISIBLE);
                        checkBox.setVisibility(View.VISIBLE);
                        select_shipping_address.setVisibility(View.VISIBLE);
                    }
                    else {
                        select_shipping_address_RecyclerView.setVisibility(View.GONE);
                        checkBox.setVisibility(View.GONE);
                        checkBox.setChecked(false);
                        select_shipping_address.setVisibility(View.GONE);
                    }
                }
            } else {
                holder.color_border.setBackgroundResource(R.drawable.add_border_white);
                System.out.println("Check_Status: " + lastCheckedPosition);
            }
        }
        public class SelectBillingAddressViewHolder extends RecyclerView.ViewHolder {
            TextView tv_credit_title,tv_address;
            RelativeLayout check_layout;
            CheckBox check;
            ConstraintLayout color_border;
            public SelectBillingAddressViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_credit_title = itemView.findViewById(R.id.tv_credit_title);
                tv_address = itemView.findViewById(R.id.tv_address);
                check_layout = itemView.findViewById(R.id.check_layout);
                check = itemView.findViewById(R.id.check);
                color_border = itemView.findViewById(R.id.color_border);
            }
        }
    }

    private void getShippingList(String userId, String account_no) {
        Call<ShippingResponse> shippingList = ApiClient.getInstance().getMontesinoApi().shippingAddress(userId, account_no);
        shippingList.enqueue(new Callback<ShippingResponse>() {
            @Override
            public void onResponse(Call<ShippingResponse> call, Response<ShippingResponse> response) {
                System.out.println("shippingAdd"+response.body().getStatusCode());
                if(response.body().getStatusCode() == 200) {
                    for(int i = 0; i < response.body().getData().size() ; i++) {
                        shippingAddressPaymentModels.add(
                                new ShippingAddressPaymentModel(
                                        response.body().getData().get(i).getAddress1(),
                                        response.body().getData().get(i).getAddress2(),
                                        response.body().getData().get(i).getCity(),
                                        response.body().getData().get(i).getCountry(),
                                        response.body().getData().get(i).getState(),
                                        response.body().getData().get(i).getZipcode(),
                                        response.body().getData().get(i).getId()));
                    }
                    if(shippingAddressPaymentModels.size() > 0) {
                       // shipping_address.setVisibility(View.GONE);
                        SelectShippingAddress adapter1 = new SelectShippingAddress(PaymentOptionActivity.this,shippingAddressPaymentModels);
                        select_shipping_address_RecyclerView.setAdapter(adapter1);
                        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(PaymentOptionActivity.this, 2);
                        select_shipping_address_RecyclerView.setLayoutManager(gridLayoutManager2);
                    }else{
                        //shipping_address.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ShippingResponse> call, Throwable t) {
                Log.e("RESPONSE CODE",""+t.getMessage());
            }
        });
    }
    public class SelectShippingAddress extends RecyclerView.Adapter<SelectShippingAddress.SelectShippingAddressViewHolder>{
        private Context mCtx;
        private   List<ShippingAddressPaymentModel> shippingAddressPaymentModels;;
        private int lastCheckedPosition = -1;
        public SelectShippingAddress(Context mCtx, List<ShippingAddressPaymentModel> shippingAddressPaymentModels) {
            this.mCtx = mCtx;
            this.shippingAddressPaymentModels = shippingAddressPaymentModels;
        }

        @NonNull
        @Override
        public SelectShippingAddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.select_shipping_address_list_adapter, null);
            return new SelectShippingAddressViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SelectShippingAddressViewHolder holder, int position) {
            ShippingAddressPaymentModel shippingAddressPaymentModel = shippingAddressPaymentModels.get(position);
            holder.tv_credit_title.setText(shippingAddressPaymentModel.getAddressTitle());
            holder.tv_credit_title.setText(mCtx.getResources().getString(R.string.address_title)+" "+String.valueOf(position+1));
            if (!shippingAddressPaymentModel.getAddress().equals("")){
                holder.tv_address.setText(shippingAddressPaymentModel.getAddressTitle()+","+shippingAddressPaymentModel.getAddress()+","+shippingAddressPaymentModel.getCity()+","+shippingAddressPaymentModel.getCountry()+","+shippingAddressPaymentModel.getState()+","+shippingAddressPaymentModel.getPinCode());
            }else {
                holder.tv_address.setText(shippingAddressPaymentModel.getAddressTitle()+","+shippingAddressPaymentModel.getCity()+","+shippingAddressPaymentModel.getCountry()+","+shippingAddressPaymentModel.getState()+","+shippingAddressPaymentModel.getPinCode());
            }

            holder.check.setChecked(shippingAddressPaymentModel.isChecked());
            holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int adapterPosition = holder.getAdapterPosition(); // Use this to get the position

                    if (isChecked) {
                        // Uncheck the last checked checkbox
                        if (lastCheckedPosition != -1 && lastCheckedPosition != adapterPosition) {
                            shippingAddressPaymentModels.get(lastCheckedPosition).setChecked(false);
                            notifyItemChanged(lastCheckedPosition);
                        }
                        lastCheckedPosition = adapterPosition;
                    }

                    shippingAddressPaymentModel.setChecked(isChecked);
                    updateConstraintLayoutBackground(holder);
                }
            });
            holder.color_border.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = holder.getAdapterPosition();
                    boolean newCheckedState = !holder.check.isChecked();
                    holder.check.setChecked(newCheckedState);

                }
            });
        }
        @Override
        public int getItemCount() {
            return shippingAddressPaymentModels.size();
        }
        private void updateConstraintLayoutBackground(SelectShippingAddressViewHolder holder) {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition == lastCheckedPosition) {
                ShippingAddressPaymentModel lastCheckedModel = shippingAddressPaymentModels.get(adapterPosition);
                if (lastCheckedModel.isChecked()) {
                    holder.color_border.setBackgroundResource(R.drawable.add_border);
                } else {
                    holder.color_border.setBackgroundResource(R.drawable.add_border_white);
                }
            } else {
                holder.color_border.setBackgroundResource(R.drawable.add_border_white);
                System.out.println("Check_Status: " + lastCheckedPosition);
            }
        }
        public class SelectShippingAddressViewHolder extends RecyclerView.ViewHolder {
            TextView tv_credit_title,tv_address;
            RelativeLayout check_layout;
            CheckBox check;
            ConstraintLayout color_border;
            public SelectShippingAddressViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_credit_title = itemView.findViewById(R.id.tv_credit_title);
                tv_address = itemView.findViewById(R.id.tv_address);
                check_layout  = itemView.findViewById(R.id.check_layout);
                check = itemView.findViewById(R.id.check);
                color_border = itemView.findViewById(R.id.color_border);
            }
        }
    }
    private void increaseInteger() {
        display(Integer.parseInt(tv_add_ext.getText().toString()) + 1);
        fetch_notarizeAmount(tv_add_ext.getText().toString());


    }
    private void decreaseInteger() {
        display(Integer.parseInt(tv_add_ext.getText().toString()) - 1);
        fetch_notarizeAmount(tv_add_ext.getText().toString());
    }
    private void display(int number) {
        tv_add_ext.setText(String.valueOf(number));
        if (String.valueOf(number).equals("0")){
            minusPlus_Ids.setVisibility(View.GONE);
            tv_add.setVisibility(View.VISIBLE);
        }else {
            minusPlus_Ids.setVisibility(View.VISIBLE);
            tv_add.setVisibility(View.GONE);
        }
    }

    private void openCard(String mode, String cardHolderName, String nickName, String enCardNumber, String expiry, String cardType, String card_id) {
        sheetDialog = new BottomSheetDialog(PaymentOptionActivity.this, R.style.BottomSheetStyleWhite);
        View card = getLayoutInflater().inflate(R.layout.add_bottomsheet_credit_card, null);
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


        sheetDialog.setContentView(card);
//        bottomSheetBehavior = BottomSheetBehavior.from((View)billingBottom.getParent());
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        sheetDialog.show();
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
    private void selectMonth() {
        final Calendar today = Calendar.getInstance();
        MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(PaymentOptionActivity.this, new MonthPickerDialog.OnDateSetListener() {
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
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.card_already_expired), Toast.LENGTH_SHORT).show();
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
    private void selectYear() {
        final Calendar today = Calendar.getInstance();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(PaymentOptionActivity.this, new MonthPickerDialog.OnDateSetListener() {
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
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.card_already_expired), Toast.LENGTH_SHORT).show();
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
        }
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
                    Toast.makeText(getApplicationContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    sheetDialog.dismiss();
                    getCardList();
                } else {
                    Toast.makeText(getApplicationContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCardResponse> call, Throwable t) {

            }
        });
    }
    private void addBillingAddress() {
        sheetDialog = new BottomSheetDialog(PaymentOptionActivity.this, R.style.BottomSheetStyleWhite);
        View billingBottom = getLayoutInflater().inflate(R.layout.add_bottomsheet_billing_card, null);
        countryIds = billingBottom.findViewById(R.id.countryIds);
        address1 = billingBottom.findViewById(R.id.address1);
        address2 = billingBottom.findViewById(R.id.address2);
        address1s = billingBottom.findViewById(R.id.address1s);
        address2s = billingBottom.findViewById(R.id.address2s);
        city = billingBottom.findViewById(R.id.citys);
        state = billingBottom.findViewById(R.id.state);
        states = billingBottom.findViewById(R.id.states);
        zipCodes = billingBottom.findViewById(R.id.zipCodes);
        saveId = billingBottom.findViewById(R.id.saveId);
        resetID = billingBottom.findViewById(R.id.resetID);
        state1 = billingBottom.findViewById(R.id.state1);
        states1 = billingBottom.findViewById(R.id.states1);
        countryIds.setFocusable(false);
        countryIds.setClickable(true);
        //
        states1.setFocusable(false);
        states1.setClickable(true);
        state.setEnabled(false);
        states.setEnabled(false);
        state.setVisibility(View.VISIBLE);
        states.setVisibility(View.VISIBLE);
        state1.setVisibility(View.GONE);
        states1.setVisibility(View.GONE);

        countryIds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCountry();

            }
        });
        states1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openState();
            }
        });
        saveId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countryIds.getText().toString().equals("")) {
                    countryIds.setError(getResources().getString(R.string.enter_country));
                    countryIds.requestFocus();
                }else if (address1s.getText().toString().equals("")) {
                    address1s.setError(getResources().getString(R.string.enter_address));
                    address1s.requestFocus();
                }else if (city.getText().toString().equals("")) {
                    city.setError(getResources().getString(R.string.enter_city));
                    city.requestFocus();
                }else if (countryIds.getText().toString().equals("United States")) {
                    if (states1.getText().toString().equals("")){
                        states1.setError(getResources().getString(R.string.enter_state));
                        states1.requestFocus();
                    }else if (zipCodes.getText().toString().equals("")) {
                        zipCodes.setError(getResources().getString(R.string.enter_zipcode));
                        zipCodes.requestFocus();
                    }else {
                        if (global.isNetworkAvailable(getApplicationContext())) {
                            saveBillingAddress(userId, account_no,countryIds.getText().toString(),address1s.getText().toString(),address2s.getText().toString(),city.getText().toString(),states1.getText().toString(),zipCodes.getText().toString());

                        } else {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.please_Check_Your_Internet_Connection), Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    if (states.getText().toString().equals("")) {
                        states.setError(getResources().getString(R.string.enter_state));
                        states.requestFocus();
                    }else if (zipCodes.getText().toString().equals("")) {
                        zipCodes.setError(getResources().getString(R.string.enter_zipcode));
                        zipCodes.requestFocus();
                    }else {
                        if (global.isNetworkAvailable(getApplicationContext())) {
                            saveBillingAddress(userId, account_no,countryIds.getText().toString(),address1s.getText().toString(),address2s.getText().toString(),city.getText().toString(),states.getText().toString(),zipCodes.getText().toString());

                        } else {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.please_Check_Your_Internet_Connection), Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            }
        });
        resetID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        sheetDialog.setContentView(billingBottom);
//        bottomSheetBehavior = BottomSheetBehavior.from((View)billingBottom.getParent());
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        sheetDialog.show();
    }
    private void addShippingAddress() {
        sheetDialog = new BottomSheetDialog(PaymentOptionActivity.this, R.style.BottomSheetStyleWhite);
        View billingBottom = getLayoutInflater().inflate(R.layout.add_bottomsheet_shipping_card, null);
        countryIds = billingBottom.findViewById(R.id.countryIds);
        address1 = billingBottom.findViewById(R.id.address1);
        address2 = billingBottom.findViewById(R.id.address2);
        address1s = billingBottom.findViewById(R.id.address1s);
        address2s = billingBottom.findViewById(R.id.address2s);
        city = billingBottom.findViewById(R.id.citys);
        zipCodes = billingBottom.findViewById(R.id.zipCodes);
        saveId = billingBottom.findViewById(R.id.saveId);
        resetID = billingBottom.findViewById(R.id.resetID);
        state1 = billingBottom.findViewById(R.id.state1);
        states1 = billingBottom.findViewById(R.id.states1);
        countryIds.setFocusable(false);
        countryIds.setClickable(true);
        //
        states1.setFocusable(false);
        states1.setClickable(true);
        getCountry();
        countryIds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openCountry();
            }
        });
        states1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openState();
            }
        });
        saveId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (address1s.getText().toString().equals("")) {
                    address1s.setError(getResources().getString(R.string.enter_address));
                    address1s.requestFocus();
                }else if (city.getText().toString().equals("")) {
                    city.setError(getResources().getString(R.string.enter_city));
                    city.requestFocus();
                }else if (states1.getText().toString().equals("")) {
                    states1.setError(getResources().getString(R.string.enter_state));
                    states1.requestFocus();
                }else if(countryIds.getText().toString().equals("")) {
                    countryIds.setError(getResources().getString(R.string.enter_country));
                    countryIds.requestFocus();
                }else if(zipCodes.getText().toString().equals("")) {
                    zipCodes.setError(getResources().getString(R.string.enter_zipcode));
                    zipCodes.requestFocus();
                }else {
                    if (global.isNetworkAvailable(getApplicationContext())) {
                        saveShippingAddress(userId, account_no,countryIds.getText().toString(),address1s.getText().toString(),address2s.getText().toString(),city.getText().toString(),states1.getText().toString(),zipCodes.getText().toString());
                    } else {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.please_Check_Your_Internet_Connection), Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        resetID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        sheetDialog.setContentView(billingBottom);
//        bottomSheetBehavior = BottomSheetBehavior.from((View)billingBottom.getParent());
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        sheetDialog.show();
    }
    public void openCountry(){
        langDialog = new Dialog(getApplicationContext());
        langDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        langDialog.setContentView(R.layout.lang_spinner_country);
        langDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
        country_list =langDialog.findViewById(R.id.country_list);
        close = langDialog.findViewById(R.id.close);
        searchBy = langDialog.findViewById(R.id.searchBy);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                langDialog.dismiss();
            }
        });

        searchBy.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(countryModel.size() > 0) {
                    countryAdapter.getFilter().filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        countryAdapter=new CountryAdapter(countryModel, getApplicationContext());
        country_list.setAdapter(countryAdapter);
        country_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        countryAdapter.notifyDataSetChanged();

        langDialog.show();
    }
    public void openState(){
        langDialog = new Dialog(getApplicationContext());
        langDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        langDialog.setContentView(R.layout.lang_spinner_state);
        langDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
        state_list =langDialog.findViewById(R.id.state_list);
        close = langDialog.findViewById(R.id.close);
        searchBy = langDialog.findViewById(R.id.searchBy);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                langDialog.dismiss();
            }
        });

        searchBy.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(stateModel.size() > 0) {
                    stateAdapter.getFilter().filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        stateAdapter=new StateAdapter(stateModel, getApplicationContext());
        state_list.setAdapter(stateAdapter);
        state_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        stateAdapter.notifyDataSetChanged();
        langDialog.show();
    }
    private void getCountry() {
        countryModel.clear();
        Call<CountryResponse> lang = ApiClient.getInstance().getMontesinoApi().getCountry();
        lang.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                if(response.body().getStatusCode() == 200) {
                    for(int i = 0; i < response.body().getData().size(); i++) {
                        countryModel.add(new CountryModel(
                                response.body().getData().get(i).getName(),
                                response.body().getData().get(i).getId()
                        ));
                    }

                }
            }
            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {

            }
        });
    }
    private void getState() {
        stateModel.clear();
        Call<CountryResponse> lang = ApiClient.getInstance().getMontesinoApi().getState();
        lang.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                if(response.body().getStatusCode() == 200) {
                    for(int i = 0; i < response.body().getData().size(); i++) {
                        stateModel.add(new StateModel(
                                response.body().getData().get(i).getName(),
                                response.body().getData().get(i).getId()
                        ));
                    }

                }
            }
            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {

            }
        });
    }
    public class CountryAdapter extends  RecyclerView.Adapter<CountryAdapter.ViewHolder> {

        List<CountryModel> countryList;
        Context context;
        List<CountryModel> countryFilterList;
        public CountryAdapter(List<CountryModel> countryList, Context context) {
            this.countryList = countryList;
            this.context = context;
            this.countryFilterList = countryList;
        }

        @NonNull
        @Override
        public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_row, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.lang_county.setText(countryList.get(position).getName());

            holder.lang_county.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setCountry(countryList.get(position).getName(), countryList.get(position).getId());
                }
            });
        }
        @Override
        public int getItemCount() {
            return countryList.size();
        }
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    FilterResults filterResults = new FilterResults();
                    if(charSequence == null || charSequence.length() == 0) {
                        filterResults.values = countryFilterList;
                        filterResults.count = countryFilterList.size();
                    } else {
                        String search = charSequence.toString().toLowerCase();
                        List<CountryModel> lList = new ArrayList<>();
                        for(CountryModel langLst: countryFilterList) {
                            if(langLst.getName().toLowerCase().contains(search)) {
                                lList.add(langLst);
                            }
                        }
                        filterResults.values = lList;
                        filterResults.count = lList.size();
                    }
                    return filterResults;
                }
                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    countryList =(List<CountryModel>)results.values;
                    notifyDataSetChanged();
                }
            };
            return filter;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView lang_county;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                lang_county = itemView.findViewById(R.id.lang_county);
            }
        }
    }

    public class StateAdapter extends  RecyclerView.Adapter<StateAdapter.ViewHolder> {

        List<StateModel> stateList;
        Context context;
        List<StateModel> stateFilterList;
        public StateAdapter(List<StateModel> stateList, Context context) {
            this.stateList = stateList;
            this.context = context;
            this.stateFilterList = stateList;
        }

        @NonNull
        @Override
        public StateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.state_row, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StateAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.lang_state.setText(stateList.get(position).getName());

            holder.lang_state.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setState(stateList.get(position).getName(), stateList.get(position).getId());

                }
            });
        }
        @Override
        public int getItemCount() {
            return stateList.size();
        }
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    FilterResults filterResults = new FilterResults();
                    if(charSequence == null || charSequence.length() == 0) {
                        filterResults.values = stateFilterList;
                        filterResults.count = stateFilterList.size();
                    } else {
                        String search = charSequence.toString().toLowerCase();
                        List<StateModel> lList = new ArrayList<>();
                        for(StateModel langLst: stateFilterList) {
                            if(langLst.getName().toLowerCase().contains(search)) {
                                lList.add(langLst);
                            }
                        }
                        filterResults.values = lList;
                        filterResults.count = lList.size();
                    }
                    return filterResults;
                }
                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    stateList =(List<StateModel>)results.values;
                    notifyDataSetChanged();
                }
            };
            return filter;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView lang_state;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                lang_state = itemView.findViewById(R.id.lang_state);
            }
        }
    }

    public void setCountry(String langName, String langId) {
        selectCountryName = langName;
        selectCountryId = langId;
        countryIds.setText(langName);
        countryIds.setError(null);
        System.out.println("CountryCode"+selectCountryId);
        if (selectCountryId.equals("226")){
            state.setVisibility(View.GONE);
            states.setVisibility(View.GONE);
            state1.setVisibility(View.VISIBLE);
            states1.setVisibility(View.VISIBLE);
        }else {
            state.setVisibility(View.VISIBLE);
            states.setVisibility(View.VISIBLE);
            state1.setVisibility(View.GONE);
            states1.setVisibility(View.GONE);
        }
        state.setEnabled(true);
        states.setEnabled(true);
        langDialog.dismiss();

    }
    public void setState(String langName, String langId) {
        selectStateName = langName;
        selectStateId = langId;
        states1.setText(langName);
        states1.setError(null);
        langDialog.dismiss();

    }

    private void saveBillingAddress(String userId, String account_no,String country, String address1, String address2, String city, String state, String zipCode) {
        System.out.println("saveBillingAddress"+" "+country+" "+address1+" "+address2+" "+city+" "+state+" "+zipCode);
        Call<BillingAddressSaveResponse> jobList = ApiClient.getInstance().getMontesinoApi().billingAddressSave(userId, account_no,address1,address2,city,state,country,zipCode);
        jobList.enqueue(new Callback<BillingAddressSaveResponse>() {
            @Override
            public void onResponse(Call<BillingAddressSaveResponse> call, Response<BillingAddressSaveResponse> response) {
                if(response.body().getStatusCode() == 200) {
                    Toast.makeText(getApplicationContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    billingAddressPaymentModes.clear();
                    getBillingAddressList(userId, account_no);
                    sheetDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<BillingAddressSaveResponse> call, Throwable t) {
                Log.e("RESPONSE CODE",""+t.getMessage());
            }
        });
    }
    private void saveShippingAddress(String userId, String account_no,String country, String address1, String address2, String city, String state, String zipCode) {
        System.out.println("saveShppingAddress"+" "+country+" "+address1+" "+address2+" "+city+" "+state+" "+zipCode);
        Call<ShippingAddressSaveResponse> shippingSave = ApiClient.getInstance().getMontesinoApi().shippingAddressSave(userId, account_no,address1,address2,city,state,country,zipCode);
        shippingSave.enqueue(new Callback<ShippingAddressSaveResponse>() {
            @Override
            public void onResponse(Call<ShippingAddressSaveResponse> call, Response<ShippingAddressSaveResponse> response) {
                if(response.body().getStatusCode() == 200) {
                    Toast.makeText(getApplicationContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    shippingAddressPaymentModels.clear();
                    getShippingList(userId, account_no);
                    sheetDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ShippingAddressSaveResponse> call, Throwable t) {
                Log.e("RESPONSE CODE",""+t.getMessage());
            }
        });
    }

    private void getCardList() {
        creditDebitCardsModels.clear();
        Call<CardListResponse> cardList = ApiClient.getInstance().getMontesinoApi().cardList(userId, account_no);
        cardList.enqueue(new Callback<CardListResponse>() {
            @Override
            public void onResponse(Call<CardListResponse> call, Response<CardListResponse> response) {
                if(response.body().getStatusCode() == 200) {
                    for(int i = 0; i < response.body().getData().size(); i++) {
                        creditDebitCardsModels.add(new CreditDebitCardsModel(
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

                    if(creditDebitCardsModels.size() > 0) {
                        creditDebitCardsAdapter = new CreditDebitCardsAdapter(PaymentOptionActivity.this, creditDebitCardsModels);
                        card_Debit_RecyclerView.setAdapter(creditDebitCardsAdapter);
                        //  card_Debit_RecyclerView.setItemViewCacheSize(10000000);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(PaymentOptionActivity.this, 2);
                        card_Debit_RecyclerView.setLayoutManager(gridLayoutManager);
                    }else {

                    }
                } else {
                    //Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CardListResponse> call, Throwable t) {

            }
        });
    }

    private void fetch_personalInformation(String userId, String account_no) {
        Call<PersonalInfoResponse> jobList = ApiClient.getInstance().getMontesinoApi().personalInfo(userId, account_no);
        jobList.enqueue(new Callback<PersonalInfoResponse>() {
            @Override
            public void onResponse(Call<PersonalInfoResponse> call, Response<PersonalInfoResponse> response) {
                if(response.body().getStatusCode() == 200) {
                    System.out.println("tex_bal"+"  "+response.body().getData().getRewardCredit());
                    translationEarned = getResources().getString(R.string.translation_earned1);
                    translationEarned_s = getResources().getString(R.string.translation_earned2);
                    tv_translation_dollars.setText(translationEarned+" "+response.body().getData().getRewardCredit()+" "+translationEarned_s);
                    translationDollars = Double.parseDouble(response.body().getData().getRewardCredit());
                    t_dollar_use.setText("$0.00");
                } else {
                    Toast.makeText(getApplicationContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PersonalInfoResponse> call, Throwable t) {
                Log.e("RESPONSE CODE",""+t.getMessage());
            }
        });
    }

    private void fetch_notarizeAmount(String countCopy) {
        Call<NotarizeAmountResponse> jobList = ApiClient.getInstance().getMontesinoApi().notarizeAmount(countCopy);
        jobList.enqueue(new Callback<NotarizeAmountResponse>() {
            @Override
            public void onResponse(Call<NotarizeAmountResponse> call, Response<NotarizeAmountResponse> response) {
                if(response.body().getStatus() == 1) {
                    System.out.println("notarize_amount"+"  "+response.body().getNotarizeAmount());
                    String getNotarizeAmount = String.valueOf(response.body().getNotarizeAmount());
                    notarized_amount.setText("$"+getNotarizeAmount);
                    try {
                        Double inputTx = Double.valueOf(response.body().getNotarizeAmount());
                        String amo = getIntent().getStringExtra("amount");
                        Double inputTx1 = Double.valueOf(amo);
                        Double tx_dollar = inputTx + inputTx1;
                        amounts = String.format("%.2f", tx_dollar);
                        totalAmount.setText(amounts);
                        dollar_amount.setText("$"+amounts);
                        System.out.println("tx_notarize_amount: " + amounts);
                        pro_amount.setText(getResources().getString(R.string.proceed_with_card)+" "+amounts);
                        Double traDollarsAmountGet = Double.valueOf(amountIds.getText().toString());
                        if (traDollarsAmountGet!=null){
                            Double traDollarsAmountSet = tx_dollar - traDollarsAmountGet;
                            String amountsSet = String.format("%.2f", traDollarsAmountSet);
                            pro_amount.setText(getResources().getString(R.string.proceed_with_card)+" "+amountsSet);
                            dollar_amount.setText("$"+amountsSet);
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid input. Please enter a valid numeric value.");
                        // Handle the exception or provide feedback to the user.
                    }
                }
            }

            @Override
            public void onFailure(Call<NotarizeAmountResponse> call, Throwable t) {
                Log.e("RESPONSE CODE",""+t.getMessage());
            }
        });
    }
    private void fetch_ValidateGiftCard(String giftCode) {
        Call<ValidateGiftCardResponse> jobList = ApiClient.getInstance().getMontesinoApi().ValidateGiftCard(giftCode);
        jobList.enqueue(new Callback<ValidateGiftCardResponse>() {
            @Override
            public void onResponse(Call<ValidateGiftCardResponse> call, Response<ValidateGiftCardResponse> response) {
                if(response.body().getStatus() == 1) {
                    pro_Validate.setVisibility(View.GONE);
                    lv_gift_pro.setVisibility(View.GONE);
                    lv_gift.setVisibility(View.VISIBLE);

                    System.out.println("giftCode_amount"+"  "+response.body().getCouponAmount());
                    try {
                        String amo = totalAmount.getText().toString();
                        Double inputAmount = Double.valueOf(amo);
                        Double amountsValue = Double.valueOf(response.body().getCouponAmount());
                        Double tx_dollar = inputAmount - amountsValue;
                        formattedResult = String.format("%.2f", tx_dollar);
                        System.out.println("giftCode_amount_minus"+"  "+formattedResult);
                        Double inputTx = Double.valueOf(formattedResult);
                        if (inputTx < 0) {
                            // Value is negative
                            pro_amount.setText(getResources().getString(R.string.proceed_with_card)+" "+"0.00");
                            String sinputTx = String.valueOf(inputTx);
                            gft_code.setText("$"+sinputTx);
                        } else {
                            // Value is non-negative
                            pro_amount.setText(getResources().getString(R.string.proceed_with_card)+" "+inputTx);
                            String sinputTx = String.valueOf(inputTx);
                            gft_code.setText("$"+sinputTx);
                        }

                        System.out.println("tx_dollar: " + formattedResult);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid input. Please enter a valid numeric value.");
                        // Handle the exception or provide feedback to the user.
                    }
                }else {
                    pro_Validate.setVisibility(View.GONE);
                    lv_gift_pro.setVisibility(View.GONE);
                    lv_gift.setVisibility(View.VISIBLE);
                    pro_amount.setText(getResources().getString(R.string.proceed_with_card)+" "+totalAmount.getText().toString());
                }
            }

            @Override
            public void onFailure(Call<ValidateGiftCardResponse> call, Throwable t) {
                Log.e("RESPONSE CODE",""+t.getMessage());
            }
        });
    }
    private void fetch_ValidateCoupon(String email,String couponCode,String invoiceAmount) {
        System.out.println("fetch_ValidateCoupon"+" "+email+" "+couponCode+" "+invoiceAmount);
        Call<ValidateCouponResponse> jobList = ApiClient.getInstance().getMontesinoApi().ValidateCoupon(email,couponCode,invoiceAmount);
        jobList.enqueue(new Callback<ValidateCouponResponse>() {
            @Override
            public void onResponse(Call<ValidateCouponResponse> call, Response<ValidateCouponResponse> response) {
                if(response.body().getStatus() == 1) {
                    pro_coupon_Validate.setVisibility(View.GONE);
                    lv_coupon_Validate.setVisibility(View.VISIBLE);
                    lv_pro_coupon_Validate.setVisibility(View.GONE);
                    System.out.println("ValidateCoupon_amount"+"  "+response.body().getCouponAmount());
                    try {
                        String amo = totalAmount.getText().toString();
                        Double inputAmount = Double.valueOf(amo);
                        Double amountsValue = Double.valueOf(response.body().getCouponAmount());
                        Double tx_dollar = inputAmount - amountsValue;
                        formattedResult = String.format("%.2f", tx_dollar);
                        Double inputTx = Double.valueOf(formattedResult);
                        if (inputTx < 0) {
                            // Value is negative
                            pro_amount.setText(getResources().getString(R.string.proceed_with_card)+" "+"0.00");
                            String sinputTx = String.valueOf(inputTx);
                            use_coupon_amount.setText("$"+sinputTx);

                        } else {
                            // Value is non-negative
                            pro_amount.setText(getResources().getString(R.string.proceed_with_card)+" "+inputTx);
                            String sinputTx = String.valueOf(inputTx);
                            use_coupon_amount.setText("$"+sinputTx);
                        }
                        System.out.println("tx_dollar: " + formattedResult);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid input. Please enter a valid numeric value.");
                        // Handle the exception or provide feedback to the user.
                    }
                }else {
                    pro_coupon_Validate.setVisibility(View.GONE);
                    lv_coupon_Validate.setVisibility(View.VISIBLE);
                    lv_pro_coupon_Validate.setVisibility(View.GONE);
                    pro_amount.setText(getResources().getString(R.string.proceed_with_card)+" "+totalAmount.getText().toString());
                }
            }

            @Override
            public void onFailure(Call<ValidateCouponResponse> call, Throwable t) {
                Log.e("RESPONSE CODE",""+t.getMessage());
            }
        });
    }
}
