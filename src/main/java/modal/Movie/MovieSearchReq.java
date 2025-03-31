package modal.Movie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSearchReq {
    private String movieName;

    private int page;
    private int pageSize;
    private  int totalPages;
    private String sortField;
    private String sortType;
}
