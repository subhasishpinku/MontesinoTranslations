package montesino.translation.montesinotranslation.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;

import montesino.translation.montesinotranslation.R;
import montesino.translation.montesinotranslation.activity.NavigationDrawerActivity;
import montesino.translation.montesinotranslation.api.ApiClient;
import montesino.translation.montesinotranslation.global.Global;
import montesino.translation.montesinotranslation.model.CardModel;
import montesino.translation.montesinotranslation.model.UserManagementModel;
import montesino.translation.montesinotranslation.response.AddUserResponse;
import montesino.translation.montesinotranslation.response.RemoveUserResponse;
import montesino.translation.montesinotranslation.response.UserChangePassword;
import montesino.translation.montesinotranslation.response.UserManagementList;
import montesino.translation.montesinotranslation.response.UserRolePermission;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserManagementFragment extends Fragment implements OnBackClickListener{
    private BackButtonHandlerInterface backButtonHandler;
    boolean doubleBackToExitPressedOnce = false;
    RecyclerView userRecyclerView;
    EditText searchBy;
    LinearLayout add_users, reload, change_role, cancelUser, saveUser, change_password, delete, cancel;
    List<UserManagementModel> userList = new ArrayList<>();
    SharedPreferences shared;
    Global global;
    String userId, account_no;
    UserManagementAdapter userManagementAdapter;
    ImageView user_icon;
    TextView user_mode;
    BottomSheetDialog sheetDialog, changePassSheetDialog;
    BottomSheetBehavior bottomSheetBehavior;
    SwitchMaterial make_admin, make_staff;
    EditText userName, userEmail, contact_no, password, cnfPassword;
    String emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

            "\\@" +

            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

            "(" +

            "\\." +

            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

            ")+";
    int user_role = 0;
    ImageView eye_btn, eye_btn_cnf;
    EditText currentPassText,passText,RepassText;
    ImageView eye_btn_current, eye_btn_change_pass, eye_btn_cnf_change_pass;
    LinearLayout passUpdate;

    RelativeLayout user_management;

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

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_management_fragement, container, false);
        global = new Global();
        initialize(rootView);
        return rootView;
    }

    private void initialize(View rootView) {
        userRecyclerView = rootView.findViewById(R.id.userRecyclerView);
        searchBy = rootView.findViewById(R.id.searchBy);
        add_users = rootView.findViewById(R.id.add_users);
        reload = rootView.findViewById(R.id.reload);
        user_management = rootView.findViewById(R.id.user_management);
        shared = getActivity().getSharedPreferences("montesino_translation", MODE_PRIVATE);
        userId = shared.getString("user_id", "");
        account_no = shared.getString("account_no", "");
        if(global.isNetworkAvailable(getContext())) {
            getUsers();
        } else {
            Toast.makeText(getContext(), getResources().getString(R.string.network_check), Toast.LENGTH_SHORT).show();
        }

        searchBy.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(userList.size() > 0) {
                    userManagementAdapter.getFilter().filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        reload.setOnClickListener(v -> {
            if(global.isNetworkAvailable(getContext())) {
                getUsers();
            } else {
                Toast.makeText(getContext(), getResources().getString(R.string.network_check), Toast.LENGTH_SHORT).show();
            }
        });

        add_users.setOnClickListener(v -> {
            addUser("Add");
        });
    }

    @SuppressLint("MissingInflatedId")
    private void addUser(String mode) {
        sheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetStyle);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View user = inflater.inflate(R.layout.add_update_users, null);

        userName = user.findViewById(R.id.userName);
        userEmail = user.findViewById(R.id.userEmail);
        contact_no = user.findViewById(R.id.contact_no);
        password = user.findViewById(R.id.password);
        cnfPassword = user.findViewById(R.id.cnfPassword);
        make_admin = user.findViewById(R.id.make_admin);
        make_staff = user.findViewById(R.id.make_staff);
        eye_btn = user.findViewById(R.id.eye_btn);
        eye_btn_cnf = user.findViewById(R.id.eye_btn_cnf);
        cancelUser = user.findViewById(R.id.cancelUser);
        saveUser = user.findViewById(R.id.saveUser);

        make_staff.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(buttonView.isPressed()) {
                if(isChecked) {
                    make_admin.setChecked(false);
                    user_role = 0;
                } else {
                    make_admin.setChecked(true);
                    user_role = 1;
                }
            }
        });

        make_admin.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(buttonView.isPressed()) {
                if(isChecked) {
                    make_staff.setChecked(false);
                    user_role = 1;
                } else {
                    make_staff.setChecked(true);
                    user_role = 0;
                }
            }
        });

        eye_btn.setOnClickListener(v -> togglePassword("Password"));

        eye_btn_cnf.setOnClickListener(v -> togglePassword("Confirm Password"));

        cancelUser.setOnClickListener(v -> {
            sheetDialog.dismiss();
        });

        saveUser.setOnClickListener(v -> {
            validateUser();
        });
        sheetDialog.setContentView(user);
        bottomSheetBehavior = BottomSheetBehavior.from((View)user.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        sheetDialog.show();
    }

    private void togglePassword(String type) {
        if(type.equals("Password")) {
            if(password.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                eye_btn.setImageResource(R.drawable.user_add_pass_eye_close);
            } else {
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                eye_btn.setImageResource(R.drawable.user_add_pass_eye_open);
            }
        } else {
            if(cnfPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                cnfPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                eye_btn_cnf.setImageResource(R.drawable.user_add_pass_eye_close);
            } else {
                cnfPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                eye_btn_cnf .setImageResource(R.drawable.user_add_pass_eye_open);
            }
        }
    }

    private void validateUser() {
        if(userName.getText().toString().equals("")) {
            userName.setError(getResources().getString(R.string.sign_up_validation_your_name));
            userName.requestFocus();
        } else if (userEmail.getText().toString().equals("")) {
            userEmail.setError(getResources().getString(R.string.sign_up_validation_your_email));
            userEmail.requestFocus();
        } else if(!userEmail.getText().toString().matches(emailPattern)) {
            userEmail.setError(getResources().getString(R.string.sign_up_validation_your_email_valid));
            userEmail.requestFocus();
        } else if (password.getText().toString().equals("")) {
            eye_btn.setVisibility(View.GONE);
            password.setError(getResources().getString(R.string.sign_up_validation_your_password));
            password.requestFocus();
        } else if (password.getText().toString().length() < 6) {
            eye_btn.setVisibility(View.GONE);
            password.setError(getResources().getString(R.string.validation_your_password_short));
            password.requestFocus();
        } else if(cnfPassword.getText().toString().equals("")) {
            eye_btn_cnf.setVisibility(View.GONE);
            cnfPassword.setError(getResources().getString(R.string.sign_up_validation_your_cnf_password));
            cnfPassword.requestFocus();
        } else if (cnfPassword.getText().toString().length() < 6) {
            eye_btn_cnf.setVisibility(View.GONE);
            cnfPassword.setError(getResources().getString(R.string.sign_up_validation_your_cnf_password_short));
            cnfPassword.requestFocus();
        } else if(password.getText().toString().equals(cnfPassword.getText().toString())) {
            eye_btn_cnf.setVisibility(View.VISIBLE);
            eye_btn.setVisibility(View.VISIBLE);
            save_user(userName.getText().toString(), userEmail.getText().toString(), contact_no.getText().toString(),
                    password.getText().toString(), user_role);

        } else {
            eye_btn_cnf.setVisibility(View.GONE);
            cnfPassword.setError(getResources().getString(R.string.sign_up_validation_your_cnf_password_wrong));
            cnfPassword.requestFocus();
        }
    }

    private void save_user(String userName, String userEmail, String contact_no, String password, int user_role) {
        String phone = "";
        if(contact_no.equals("")) {
            phone = "";
        } else {
            phone = contact_no;
        }
        Call<AddUserResponse> addUser = ApiClient.getInstance().getMontesinoApi().addUser(userId, userName, userEmail, phone, password, user_role);
        addUser.enqueue(new Callback<AddUserResponse>() {
            @Override
            public void onResponse(Call<AddUserResponse> call, Response<AddUserResponse> response) {
                if(response.body().getStatusCode() == 200) {
                    Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    sheetDialog.dismiss();
                    getUsers();
                }
            }

            @Override
            public void onFailure(Call<AddUserResponse> call, Throwable t) {

            }
        });
    }

    private void getUsers() {
        userList.clear();
        Call<UserManagementList> userListCall = ApiClient.getInstance().getMontesinoApi().userList(userId, account_no);
        userListCall.enqueue(new Callback<UserManagementList>() {
            @Override
            public void onResponse(Call<UserManagementList> call, Response<UserManagementList> response) {
                if(response.body().getStatusCode() == 200) {
                    for(int i = 0; i< response.body().getData().size(); i++) {
                        userList.add(new UserManagementModel(
                                response.body().getData().get(i).getId(),
                                response.body().getData().get(i).getAccountNo(),
                                response.body().getData().get(i).getEmail(),
                                response.body().getData().get(i).getCompany(),
                                response.body().getData().get(i).getName(),
                                response.body().getData().get(i).getIsCompanyAdmin(),
                                response.body().getData().get(i).getIsCompanyAdminMe()
                        ));
                    }
                    if(userList.size() > 0) {
                        user_management.setVisibility(View.GONE);
                        userManagementAdapter = new UserManagementAdapter(userList, getContext());
                        userRecyclerView.setAdapter(userManagementAdapter);
                        userRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        userManagementAdapter.notifyDataSetChanged();
                    }
                    else {
                        user_management.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserManagementList> call, Throwable t) {

            }
        });
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

    public class UserManagementAdapter extends RecyclerView.Adapter<UserManagementAdapter.ViewHolder> {

        List<UserManagementModel> userList;
        Context context;
        List<UserManagementModel> userFilterList;
        BottomSheetDialog sheetDialog;

        public UserManagementAdapter(List<UserManagementModel> userList, Context context) {
            this.userList = userList;
            this.context = context;
            this.userFilterList = userList;
        }

        @NonNull
        @Override
        public UserManagementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull UserManagementAdapter.ViewHolder holder, int position) {
            holder.user_name.setText(userList.get(position).getName());
            holder.account_no.setText(context.getResources().getString(R.string.acc_no) + " " + userList.get(position).getAccount_no());
            holder.email_address.setText(userList.get(position).getEmail());
            if(userList.get(position).getCompanyName() != null) {
                holder.company_name.setText(userList.get(position).getCompanyName());
            } else {
                holder.company_name.setVisibility(View.GONE);
            }

            if(!userList.get(position).getIs_company_admin_me().equals("1")) {
                holder.user_icon_layout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.left_side_user_alt));
                if(userList.get(position).getIs_company_admin().equals("1")) {
                    holder.user_icon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.admin_ico));
                } else {
                    holder.user_icon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.default_user));
                }
            } else {
                holder.user_icon_layout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.left_side_user));
            }

            holder.menu_bar_layout.setOnClickListener(v -> {
                openView(position);
            });
        }

        @SuppressLint("MissingInflatedId")
        private void openView(int position) {
            sheetDialog = new BottomSheetDialog(context, R.style.BottomSheetStyle);
            LayoutInflater inflater = LayoutInflater.from(context);
            View userOptions = inflater.inflate(R.layout.user_management_option, null);

            user_icon = userOptions.findViewById(R.id.user_icon);
            user_mode = userOptions.findViewById(R.id.user_mode);
            change_role = userOptions.findViewById(R.id.change_role);
            change_password = userOptions.findViewById(R.id.change_password);
            delete = userOptions.findViewById(R.id.delete);
            cancel = userOptions.findViewById(R.id.cancel);

            if(userList.get(position).getIs_company_admin_me().equals("1")) {
                delete.setVisibility(View.GONE);
            } else {
                delete.setVisibility(View.VISIBLE);
            }

            change_password.setOnClickListener(v -> {
                sheetDialog.dismiss();
                changePassword(position);
            });

            change_role.setOnClickListener(v -> {
                showAlert(position);
                sheetDialog.dismiss();
            });

            delete.setOnClickListener(v -> {
                showRemoveAlert(position);
                sheetDialog.dismiss();
            });

            cancel.setOnClickListener(v -> sheetDialog.dismiss());

            if(userList.get(position).getIs_company_admin_me().equals("0")) {
                if (userList.get(position).getIs_company_admin().equals("0")) {
                    user_icon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.admin_ico_black));

                    user_mode.setText(context.getResources().getString(R.string.make_admin));
                } else {
                    user_icon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.user_ico));
                    user_mode.setText(context.getResources().getString(R.string.make_staff));
                }
            } else {
                change_role.setVisibility(View.GONE);
            }

            sheetDialog.setContentView(userOptions);
            sheetDialog.show();
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }

        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    FilterResults filterResults = new FilterResults();
                    if(charSequence == null || charSequence.length() == 0) {
                        filterResults.values = userFilterList;
                        filterResults.count = userFilterList.size();
                    } else {
                        String search = charSequence.toString().toLowerCase();
                        List<UserManagementModel> cList = new ArrayList<>();
                        for(UserManagementModel cFilterList: userFilterList) {
                            if(cFilterList.getName().toLowerCase().contains(search) || cFilterList.getEmail().toLowerCase().contains(search) || cFilterList.getAccount_no().toLowerCase().contains(search)) {
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
                    userList = (List<UserManagementModel>)results.values;
                    notifyDataSetChanged();
                }
            };
            return filter;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            LinearLayout user_icon_layout, menu_bar_layout;
            ImageView user_icon;
            TextView user_name, account_no, email_address, company_name;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                user_icon_layout = itemView.findViewById(R.id.user_icon_layout);
                menu_bar_layout = itemView.findViewById(R.id.menu_bar_layout);
                user_icon = itemView.findViewById(R.id.user_icon);
                user_name = itemView.findViewById(R.id.user_name);
                account_no = itemView.findViewById(R.id.account_no);
                email_address = itemView.findViewById(R.id.email_address);
                company_name = itemView.findViewById(R.id.company_name);
            }
        }
    }

    private void showRemoveAlert(int position) {
        AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setMessage(getResources().getString(R.string.remove_user));
        builder.setPositiveButton(getResources().getString(R.string.no), (dialog, which) -> {
            dialog.dismiss();
        });
        builder.setNegativeButton(getResources().getString(R.string.yes), (dialogInterface, i) -> {
            if(global.isNetworkAvailable(getContext())) {
                userRemove(userList.get(position).getId());
            } else {
                Toast.makeText(getContext(), getResources().getString(R.string.please_Check_Your_Internet_Connection), Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void userRemove(String update_user_id) {
        Call<RemoveUserResponse> removeUsers = ApiClient.getInstance().getMontesinoApi().removeUser(update_user_id, userId);
        removeUsers.enqueue(new Callback<RemoveUserResponse>() {
            @Override
            public void onResponse(Call<RemoveUserResponse> call, Response<RemoveUserResponse> response) {
                Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                if(response.body().getStatusCode() == 200) {
                    getUsers();
                }
            }

            @Override
            public void onFailure(Call<RemoveUserResponse> call, Throwable t) {

            }
        });
    }

    private void showAlert(int position) {
        AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setMessage(getResources().getString(R.string.change_permission));
        builder.setPositiveButton(getResources().getString(R.string.no), (dialog, which) -> {
            dialog.dismiss();
        });
        builder.setNegativeButton(getResources().getString(R.string.yes), (dialogInterface, i) -> {
            if(global.isNetworkAvailable(getContext())) {
                changeRolePermission(userList.get(position).getId());
                //removeImage("Bearer "+auth, str_ids);

            } else {
                Toast.makeText(getContext(), getResources().getString(R.string.please_Check_Your_Internet_Connection), Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void changeRolePermission(String update_user_id) {
        Call<UserRolePermission> changePermission = ApiClient.getInstance().getMontesinoApi().changePermission(update_user_id, userId, account_no);
        changePermission.enqueue(new Callback<UserRolePermission>() {
            @Override
            public void onResponse(Call<UserRolePermission> call, Response<UserRolePermission> response) {
                Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                if(response.body().getStatusCode() == 200) {
                    getUsers();
                }
            }

            @Override
            public void onFailure(Call<UserRolePermission> call, Throwable t) {

            }
        });
    }

    @SuppressLint("MissingInflatedId")
    private void changePassword(int position) {
        changePassSheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetStyle);
        View changePass = getLayoutInflater().inflate(R.layout.chnage_password_user_management, null);

        currentPassText  = changePass.findViewById(R.id.currentPassIds);
        passText  = changePass.findViewById(R.id.passIds);
        RepassText  = changePass.findViewById(R.id.RepassIds);
        passUpdate = changePass.findViewById(R.id.passUpdate);
        eye_btn_current = changePass.findViewById(R.id.eye_btn_current);
        eye_btn_change_pass = changePass.findViewById(R.id.eye_btn);
        eye_btn_cnf_change_pass = changePass.findViewById(R.id.eye_btn_cnf);

        eye_btn_change_pass.setOnClickListener(v -> {
            toggleChangePassword("Password");
        });

        eye_btn_cnf_change_pass.setOnClickListener(v -> {
            toggleChangePassword("Confirm");
        });

        passUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passText.getText().toString().equals("")) {
                    passText.setError(getResources().getString(R.string.enter_your_new_password));
                    passText.requestFocus();
                }else if(passText.getText().toString().length() < 6) {
                    passText.setError(getResources().getString(R.string.new_password_must_be_6_character_long));
                    passText.requestFocus();
                }else if(RepassText.getText().toString().equals("")) {
                    RepassText.setError(getResources().getString(R.string.re_enter_new_password));
                    RepassText.requestFocus();
                }else if(passText.getText().toString().equals(RepassText.getText().toString())) {
                    if(global.isNetworkAvailable(getContext())) {
                        updatePass(currentPassText.getText().toString(),passText.getText().toString(),userId,userList.get(position).getId());
                    } else {
                        String please_Check_Your_Internet_Connection = getResources().getString(R.string.please_Check_Your_Internet_Connection);
                        Toast.makeText(getContext(), please_Check_Your_Internet_Connection, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    RepassText.setError(getResources().getString(R.string.confirm_password_mismatch));
                    RepassText.requestFocus();
                }
            }
        });

        changePassSheetDialog.setContentView(changePass);
        changePassSheetDialog.show();
    }

    private void toggleChangePassword(String type) {
        if(type.equals("Password")) {
            if(passText.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                passText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                eye_btn_change_pass.setImageResource(R.drawable.user_add_pass_eye_close);
            } else {
                passText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                eye_btn_change_pass.setImageResource(R.drawable.user_add_pass_eye_open);
            }
        } else if (type.equals("Current")) {
            if(currentPassText.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                currentPassText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                eye_btn_current.setImageResource(R.drawable.user_add_pass_eye_close);
            } else {
                currentPassText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                eye_btn_current.setImageResource(R.drawable.user_add_pass_eye_open);
            }
        } else {
            if(RepassText.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                RepassText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                eye_btn_cnf_change_pass.setImageResource(R.drawable.user_add_pass_eye_close);
            } else {
                RepassText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                eye_btn_cnf_change_pass .setImageResource(R.drawable.user_add_pass_eye_open);
            }
        }
    }

    private void updatePass(String currentPass, String password, String session_id, String userId) {
        Call<UserChangePassword> userPasswordChange = ApiClient.getInstance().getMontesinoApi().changePassword(userId, session_id, currentPass, password);
        userPasswordChange.enqueue(new Callback<UserChangePassword>() {
            @Override
            public void onResponse(Call<UserChangePassword> call, Response<UserChangePassword> response) {
                Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                changePassSheetDialog.dismiss();
            }

            @Override
            public void onFailure(Call<UserChangePassword> call, Throwable t) {

            }
        });
    }

}