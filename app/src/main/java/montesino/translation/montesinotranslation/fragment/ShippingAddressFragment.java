package montesino.translation.montesinotranslation.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import montesino.translation.montesinotranslation.R;
import montesino.translation.montesinotranslation.api.ApiClient;
import montesino.translation.montesinotranslation.global.Global;
import montesino.translation.montesinotranslation.model.CountryModel;
import montesino.translation.montesinotranslation.model.ShippingModel;
import montesino.translation.montesinotranslation.model.StateModel;
import montesino.translation.montesinotranslation.response.BillingAddressDeleteResponse;
import montesino.translation.montesinotranslation.response.BillingAddressSaveResponse;
import montesino.translation.montesinotranslation.response.CountryResponse;
import montesino.translation.montesinotranslation.response.ShippingAddressDeleteResponse;
import montesino.translation.montesinotranslation.response.ShippingAddressSaveResponse;
import montesino.translation.montesinotranslation.response.ShippingResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingAddressFragment extends Fragment implements OnBackClickListener{
    private BackButtonHandlerInterface backButtonHandler;
    boolean doubleBackToExitPressedOnce = false;
    RecyclerView shippingRecyclerView,country_list,state_list;
    View rootView;
    SharedPreferences shared;
    String userId, account_no;
    RelativeLayout rv_scroll;
    List<ShippingModel> shippingModels = new ArrayList<>();
    ImageView add_Id,close;
    BottomSheetDialog sheetDialog;
    BottomSheetBehavior<View> bottomSheetBehavior;
    TextInputEditText countryIds,address1s,citys,zipCodes,address2s;
    TextInputLayout address1,address2,state1;
    LinearLayout saveId,resetID;
    EditText states1,searchBy;
    Dialog langDialog;
    Global global;
    CountryAdapter countryAdapter;
    StateAdapter stateAdapter;
    List<CountryModel> countryModel = new ArrayList<>();
    List<StateModel> stateModel = new ArrayList<>();
    String selectCountryName,selectCountryId,selectStateName,selectStateId;
    RelativeLayout shipping_address;
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
        rootView = inflater.inflate(R.layout.shipping_address_fragment, container, false);
        initialize();
        return rootView;
    }
    private void initialize() {
        shippingRecyclerView = (RecyclerView) rootView.findViewById(R.id.shippingRecyclerView);
        add_Id =  (ImageView) rootView.findViewById(R.id.add_Id);
        shipping_address =  (RelativeLayout) rootView.findViewById(R.id.shipping_address);
        shippingRecyclerView.setNestedScrollingEnabled(false);
        rv_scroll = (RelativeLayout) rootView.findViewById(R.id.rv_scroll);
        shared = getActivity().getSharedPreferences("montesino_translation", MODE_PRIVATE);
        userId = shared.getString("user_id", "");
        account_no = shared.getString("account_no", "");
        global = new Global();
        add_Id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addShippingAddress();
            }
        });
        getState();
        getShippingList(userId, account_no);
    }

    private void addShippingAddress() {
        sheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetStyleWhite);
        View billingBottom = getLayoutInflater().inflate(R.layout.add_bottomsheet_shpping_address, null);
        countryIds = billingBottom.findViewById(R.id.countryIds);
        address1 = billingBottom.findViewById(R.id.address1);
        address2 = billingBottom.findViewById(R.id.address2);
        address1s = billingBottom.findViewById(R.id.address1s);
        address2s = billingBottom.findViewById(R.id.address2s);
        citys = billingBottom.findViewById(R.id.citys);
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
                }else if (citys.getText().toString().equals("")) {
                    citys.setError(getResources().getString(R.string.enter_city));
                    citys.requestFocus();
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
                   if (global.isNetworkAvailable(getContext())) {
                       saveShippingAddress(userId, account_no,countryIds.getText().toString(),address1s.getText().toString(),address2s.getText().toString(),citys.getText().toString(),states1.getText().toString(),zipCodes.getText().toString());
                   } else {
                       Toast.makeText(getContext(), getResources().getString(R.string.please_Check_Your_Internet_Connection), Toast.LENGTH_SHORT).show();
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
    private void saveShippingAddress(String userId, String account_no,String country, String address1, String address2, String city, String state, String zipCode) {
        System.out.println("saveShppingAddress"+" "+country+" "+address1+" "+address2+" "+city+" "+state+" "+zipCode);
        Call<ShippingAddressSaveResponse> shippingSave = ApiClient.getInstance().getMontesinoApi().shippingAddressSave(userId, account_no,address1,address2,city,state,country,zipCode);
        shippingSave.enqueue(new Callback<ShippingAddressSaveResponse>() {
            @Override
            public void onResponse(Call<ShippingAddressSaveResponse> call, Response<ShippingAddressSaveResponse> response) {
                if(response.body().getStatusCode() == 200) {
                    Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    shippingModels.clear();
                    getShippingList(userId, account_no);
                    sheetDialog.dismiss();
                } else {
                    Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ShippingAddressSaveResponse> call, Throwable t) {
                Log.e("RESPONSE CODE",""+t.getMessage());
            }
        });
    }
    public void openCountry(){
        langDialog = new Dialog(getContext());
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

//        countryAdapter=new CountryAdapter(countryModel, getContext());
//        country_list.setAdapter(countryAdapter);
//        country_list.setLayoutManager(new LinearLayoutManager(getContext()));
//        countryAdapter.notifyDataSetChanged();

        langDialog.show();
    }
    public void openState(){
        langDialog = new Dialog(getContext());
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
        stateAdapter=new StateAdapter(stateModel, getContext());
        state_list.setAdapter(stateAdapter);
        state_list.setLayoutManager(new LinearLayoutManager(getContext()));
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
                        if (response.body().getData().get(i).getName().equals("United States")){
                            countryIds.setText(response.body().getData().get(i).getName());
                        }
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
    private void getShippingList(String userId, String account_no) {
        Call<ShippingResponse> shippingList = ApiClient.getInstance().getMontesinoApi().shippingAddress(userId, account_no);
        shippingList.enqueue(new Callback<ShippingResponse>() {
            @Override
            public void onResponse(Call<ShippingResponse> call, Response<ShippingResponse> response) {
                System.out.println("shippingAdd"+response.body().getStatusCode());
                if(response.body().getStatusCode() == 200) {
                    for(int i = 0; i < response.body().getData().size() ; i++) {
                        shippingModels.add(
                                new ShippingModel(
                                        response.body().getData().get(i).getAddress1(),
                                        response.body().getData().get(i).getAddress2(),
                                        response.body().getData().get(i).getCity(),
                                        response.body().getData().get(i).getCountry(),
                                        response.body().getData().get(i).getState(),
                                        response.body().getData().get(i).getZipcode(),
                                        response.body().getData().get(i).getId()));
                    }
                    if(shippingModels.size() > 0) {
                        shipping_address.setVisibility(View.GONE);
                        shippingRecyclerView.setHasFixedSize(true);
                        shippingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        ShippingAdapter adapter = new ShippingAdapter(getContext(), shippingModels);
                        shippingRecyclerView.setAdapter(adapter);
                    }else{
                        shipping_address.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ShippingResponse> call, Throwable t) {
                Log.e("RESPONSE CODE",""+t.getMessage());
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
            return new CountryAdapter.ViewHolder(view);
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
            return new StateAdapter.ViewHolder(view);
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
        System.out.println("CountryCode"+selectCountryId);
        langDialog.dismiss();

    }
    public void setState(String langName, String langId) {
        selectStateName = langName;
        selectStateId = langId;
        states1.setText(langName);
        states1.setError(null);
        langDialog.dismiss();

    }

    public class ShippingAdapter extends RecyclerView.Adapter<ShippingAdapter.ShippingViewHolder> {
        private Context mCtx;
        private List<ShippingModel> shippingModels;
        public ShippingAdapter(Context mCtx, List<ShippingModel> shippingModels) {
            this.mCtx = mCtx;
            this.shippingModels = shippingModels;
        }
        @Override
        public ShippingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.shipping_list_adapter, null);
            return new ShippingViewHolder(view);
        }
        @Override
        public void onBindViewHolder(ShippingAdapter.ShippingViewHolder holder, @SuppressLint("RecyclerView") int position) {
            //getting the product of the specified position
            ShippingModel shippingModel = shippingModels.get(position);
            holder.shipping_addressTitle.setText(mCtx.getResources().getString(R.string.address_title)+" "+String.valueOf(position+1));
            holder.shipping_address.setText(shippingModel.getAddressTitle());
            if (!shippingModel.getAddress().equals("")){
                holder.shipping_address1.setText(shippingModel.getAddress());
            }else {
                holder.shipping_address1.setVisibility(View.GONE);
            }
            holder.cityCountryStateID.setText(shippingModel.getCity()+","+shippingModel.getCountry()+","+shippingModel.getState());
            holder.pinCodeId.setText(shippingModel.getPinCode());
            holder.delete_Id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteShippingAddress(shippingModel.getRowIds(),userId,account_no);
                }
            });

        }
        private void deleteShippingAddress(String rowIds,String userId, String account_no) {
            Call<ShippingAddressDeleteResponse> shippingDelete = ApiClient.getInstance().getMontesinoApi().shippingAddressDelete(rowIds, userId,account_no);
            shippingDelete.enqueue(new Callback<ShippingAddressDeleteResponse>() {
                @Override
                public void onResponse(Call<ShippingAddressDeleteResponse> call, Response<ShippingAddressDeleteResponse> response) {
                    System.out.println("shippingAdd"+response.body().getStatusCode());
                    if(response.body().getStatusCode() == 200) {
                        Toast.makeText(mCtx, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        shippingModels.clear();
                        getShippingList(userId, account_no);
                    } else {
                        Toast.makeText(mCtx, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ShippingAddressDeleteResponse> call, Throwable t) {
                    Log.e("RESPONSE CODE",""+t.getMessage());
                }
            });
        }
        @Override
        public int getItemCount() {
            return shippingModels.size();
        }
        class ShippingViewHolder extends RecyclerView.ViewHolder {
            TextView shipping_addressTitle,shipping_address,shipping_address1,cityCountryStateID,pinCodeId;
            ImageView delete_Id;
            public ShippingViewHolder(View itemView) {
                super(itemView);
                shipping_addressTitle = itemView.findViewById(R.id.shipping_addressTitle);
                shipping_address = itemView.findViewById(R.id.shipping_address);
                shipping_address1 = itemView.findViewById(R.id.shipping_address1);
                cityCountryStateID = itemView.findViewById(R.id.cityCountryStateID);
                pinCodeId = itemView.findViewById(R.id.pinCodeId);
                delete_Id = itemView.findViewById(R.id.delete_Id);
            }
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
}
