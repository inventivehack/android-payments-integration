package androidtitlan.gdg.com.processpayments_example.payments.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidtitlan.gdg.com.processpayments_example.R;
import androidtitlan.gdg.com.processpayments_example.payments.view.viewmodel.PaymentViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter para mostrar la información de las opciones de pago.
 */
public class PaymentsAdapter extends RecyclerView.Adapter<PaymentsViewHolder> {

  private Context mContext;
  private LayoutInflater inflater;

  private List<PaymentViewModel> mViewModelList;

  public PaymentsAdapter(Context context) {
    mContext = context;
    inflater = LayoutInflater.from(mContext);
    mViewModelList = new ArrayList<>();
  }

  public void addAll(List<PaymentViewModel> viewModelList) {
    int previousSize = mViewModelList.size();
    mViewModelList.addAll(viewModelList);
    notifyItemRangeInserted(previousSize, viewModelList.size());
  }

  public void setAll(List<PaymentViewModel> viewModelList) {
    mViewModelList = viewModelList;
    notifyDataSetChanged();
  }

  public boolean isEmptyList() {
    return mViewModelList.isEmpty();
  }

  @Override public PaymentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View viewItem = inflater.inflate(R.layout.item_payment, parent, false);
    return new PaymentsViewHolder(viewItem);
  }

  @Override public void onBindViewHolder(PaymentsViewHolder holder, int position) {
    holder.render(mViewModelList.get(position));
  }

  @Override public int getItemCount() {
    return mViewModelList.size();
  }
}
