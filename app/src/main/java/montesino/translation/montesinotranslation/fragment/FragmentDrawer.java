package montesino.translation.montesinotranslation.fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import montesino.translation.montesinotranslation.R;
import montesino.translation.montesinotranslation.global.Consts;
import montesino.translation.montesinotranslation.model.MyAccountList;

public class FragmentDrawer extends Fragment {
    private static String TAG = FragmentDrawer.class.getSimpleName();
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View containerView;
    private FragmentDrawerListener drawerListener;
    private SharedPreferences sp;
    RelativeLayout rv_orderNow,rv_submitAQuote,rv_job,rv_MyAccount,rv_TrackOrder,rv_UserManagement,rv_SignOut;
    ImageView imageView_dawn_arrow,imageView_up_arrow;
    TextView MyAccount_text,MyAccount_textSmall;
    RecyclerView my_account_list;
    List<MyAccountList> myAccountLists = new ArrayList<>();
    public FragmentDrawer() {
    }
    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sp  =   this.getActivity().getSharedPreferences(Consts.SP_NAME, Context.MODE_PRIVATE);
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        rv_orderNow = (RelativeLayout) layout.findViewById(R.id.rv_orderNow);
        rv_submitAQuote = (RelativeLayout) layout.findViewById(R.id.rv_submitAQuote);
        rv_job = (RelativeLayout) layout.findViewById(R.id.rv_job);
        rv_MyAccount = (RelativeLayout) layout.findViewById(R.id.rv_MyAccount);
        rv_TrackOrder = (RelativeLayout) layout.findViewById(R.id.rv_TrackOrder);
        rv_UserManagement = (RelativeLayout) layout.findViewById(R.id.rv_UserManagement);
        rv_SignOut = (RelativeLayout) layout.findViewById(R.id.rv_SignOut);
        imageView_dawn_arrow = (ImageView) layout.findViewById(R.id.imageView_dawn_arrow);
        imageView_up_arrow = (ImageView) layout.findViewById(R.id.imageView_up_arrow);
        MyAccount_text = (TextView) layout.findViewById(R.id.MyAccount_text);
        MyAccount_textSmall = (TextView) layout.findViewById(R.id.MyAccount_textSmall);
        my_account_list = (RecyclerView) layout.findViewById(R.id.my_account_list);
        imageView_dawn_arrow.setVisibility(View.GONE);
        my_account_list.setVisibility(View.GONE);
        imageView_dawn_arrow.setVisibility(View.VISIBLE);
        imageView_dawn_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView_up_arrow.setVisibility(View.VISIBLE);
                imageView_dawn_arrow.setVisibility(View.GONE);
                my_account_list.setVisibility(View.VISIBLE);
            }
        });
        imageView_up_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView_dawn_arrow.setVisibility(View.VISIBLE);
                imageView_up_arrow.setVisibility(View.GONE);
                my_account_list.setVisibility(View.GONE);
            }
        });

        rv_orderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListener.onDrawerItemSelected(view, Consts.ORDERNOW);
                mDrawerLayout.closeDrawer(containerView);
            }
        });
        rv_submitAQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListener.onDrawerItemSelected(view, Consts.SUBMITAQUOTE);
                mDrawerLayout.closeDrawer(containerView);
            }
        });
        rv_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("DisplayView"+" "+"position");
                drawerListener.onDrawerItemSelected(view, Consts.JOBS);
                mDrawerLayout.closeDrawer(containerView);
            }
        });

        MyAccount_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListener.onDrawerItemSelected(view, Consts.MYACCOUNT);
                mDrawerLayout.closeDrawer(containerView);
            }
        });
        MyAccount_textSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListener.onDrawerItemSelected(view, Consts.MYACCOUNT);
                mDrawerLayout.closeDrawer(containerView);
            }
        });
        rv_TrackOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListener.onDrawerItemSelected(view, Consts.TRACKORDER);
                mDrawerLayout.closeDrawer(containerView);
            }
        });
        rv_UserManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListener.onDrawerItemSelected(view, Consts.USERMANAGEMENT);
                mDrawerLayout.closeDrawer(containerView);
            }
        });
        rv_SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListener.onDrawerItemSelected(view, Consts.SIGNOUT);
                mDrawerLayout.closeDrawer(containerView);
            }
        });
        myAccountLists.add(
                new MyAccountList(
                        "Personal Information"
                        ));
        myAccountLists.add(
                new MyAccountList(
                        "Credit/Debit Cards"));
        myAccountLists.add(
                new MyAccountList(
                        "Shipping Address"));
        myAccountLists.add(
                new MyAccountList(
                        "Billing Address"
                        ));
        myAccountLists.add(
                new MyAccountList(
                        "Payment History"));
        myAccountLists.add(
                new MyAccountList("Change Password"));
        my_account_list.setHasFixedSize(true);
        my_account_list.setLayoutManager(new LinearLayoutManager(getContext()));
        MyaccountAdapter adapter = new MyaccountAdapter(getContext(), myAccountLists);
        my_account_list.setAdapter(adapter);
        return layout;
    }
    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }
    public interface FragmentDrawerListener {
        public void onDrawerItemSelected(View view, int position);
    }

    public class MyaccountAdapter extends RecyclerView.Adapter<MyaccountAdapter.myaccountListViewHolder> {
        private Context mCtx;
        private List<MyAccountList> myAccountLists;
        public MyaccountAdapter(Context mCtx, List<MyAccountList> myAccountLists) {
            this.mCtx = mCtx;
            this.myAccountLists = myAccountLists;
        }
        @Override
        public MyaccountAdapter.myaccountListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.my_accont_list_adapter, null);
            return new MyaccountAdapter.myaccountListViewHolder(view);
        }
        @Override
        public void onBindViewHolder(MyaccountAdapter.myaccountListViewHolder holder, int position) {
            //getting the product of the specified position
            MyAccountList myAccountList = myAccountLists.get(position);
            holder.my_account_list_name.setText(myAccountList.getMyAccountList());
            holder.lv_acc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myAccountList.getMyAccountList().equals("Personal Information")){
                        drawerListener.onDrawerItemSelected(v, Consts.PERSONAL_INFORMATION);
                        mDrawerLayout.closeDrawer(containerView);
                    } else if (myAccountList.getMyAccountList().equals("Credit/Debit Cards")) {
                        drawerListener.onDrawerItemSelected(v, Consts.CREDIT_DEBIT_CARDS);
                        mDrawerLayout.closeDrawer(containerView);
                    }else if (myAccountList.getMyAccountList().equals("Shipping Address")) {
                        drawerListener.onDrawerItemSelected(v, Consts.SHIPPING_ADDRESS);
                        mDrawerLayout.closeDrawer(containerView);
                    }else if (myAccountList.getMyAccountList().equals("Billing Address")) {
                        drawerListener.onDrawerItemSelected(v, Consts.BILLING_ADDRESS);
                        mDrawerLayout.closeDrawer(containerView);
                    }else if (myAccountList.getMyAccountList().equals("Payment History")) {
                        drawerListener.onDrawerItemSelected(v, Consts.PAYMENT_HISTORY);
                        mDrawerLayout.closeDrawer(containerView);
                    }else if (myAccountList.getMyAccountList().equals("Change Password")) {
                        drawerListener.onDrawerItemSelected(v, Consts.CHANGE_PASSWORD);
                        mDrawerLayout.closeDrawer(containerView);
                    }else {

                    }
                }
            });
        }
        @Override
        public int getItemCount() {
            return myAccountLists.size();
        }
        class myaccountListViewHolder extends RecyclerView.ViewHolder {
            TextView my_account_list_name;
            LinearLayout lv_acc;
            public myaccountListViewHolder(View itemView) {
                super(itemView);
                my_account_list_name = itemView.findViewById(R.id.my_account_list_name);
                lv_acc = itemView.findViewById(R.id.lv_acc);
            }
        }
    }

}
