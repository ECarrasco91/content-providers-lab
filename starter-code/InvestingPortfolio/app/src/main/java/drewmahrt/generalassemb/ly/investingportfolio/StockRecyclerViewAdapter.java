package drewmahrt.generalassemb.ly.investingportfolio;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by drewmahrt on 11/22/16.
 */

public class StockRecyclerViewAdapter extends RecyclerView.Adapter<StockRecyclerViewAdapter.StockViewHolder>{

    List<Stock> mStockList;

    public StockRecyclerViewAdapter(List<Stock> stockList) {
        mStockList = stockList;
    }


    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StockViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_item, parent, false));
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {
        holder.mCompanyName.setText(mStockList.get(position).getStockName());
        holder.mQuantityOfStock.setText(mStockList.get(position).getStockCount());
    }

    @Override
    public int getItemCount() {
        return mStockList.size();
    }

    public void swapData(Cursor cursor){
        mStockList.clear();

        if(cursor != null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                long id = cursor.getLong(cursor.getColumnIndex(StockPortfolioContract.Stocks._ID));
                String name = cursor.getString(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_STOCKNAME));
                int count = cursor.getInt(cursor.getColumnIndex(StockPortfolioContract.Stocks.COLUMN_QUANTITY));
                mStockList.add(new Stock(name,count,id));
                cursor.moveToNext();
            }
        }

        notifyDataSetChanged();
    }


    public class StockViewHolder extends RecyclerView.ViewHolder{
        TextView mCompanyName, mQuantityOfStock;

        public StockViewHolder(View itemView) {
            super(itemView);
            mCompanyName = (TextView) itemView.findViewById(R.id.company_name);
            mQuantityOfStock = (TextView) itemView.findViewById(R.id.quantity_of_stock);
        }
    }

}
