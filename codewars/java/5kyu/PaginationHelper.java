import java.util.*;

public class PaginationHelper<T> {
    List<T> collection;
    int itemsPerPage;
    List<Integer> pages; 
    
    public PaginationHelper(List<T> collection, int itemsPerPage) {
        this.collection = collection;
        this.itemsPerPage = itemsPerPage;
        this.pages = this.makePages();
    }

    private List<Integer> makePages() {
        List<Integer> list = new ArrayList<>(0);
        int items = this.itemCount();

        for (int i = 0; i < this.pageCount(); i++) {
            int adding = (items > this.itemsPerPage) ? this.itemsPerPage : items;
            list.add(adding);
            items -= adding;
        }

        return list;
    }

    public int itemCount() {
        return this.collection.size();
    }

    public int pageCount() {
        return (int) Math.ceil((double) this.itemCount() / (double) this.itemsPerPage);
    }

    public int pageItemCount(int pageIndex) {
        if (pageIndex < 0)  return -1;
        return (pageIndex < this.pageCount()) ? this.pages.get(pageIndex) : -1;
    }
    
    public int pageIndex(int itemIndex) {
        if (itemIndex < 0)  return -1;
        return (itemIndex < this.itemCount()) ? itemIndex / this.itemsPerPage : -1;
    }
}