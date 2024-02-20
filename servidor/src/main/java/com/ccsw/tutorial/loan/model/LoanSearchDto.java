package com.ccsw.tutorial.loan.model;

import com.ccsw.tutorial.common.pagination.PageableRequest;

/**
 * @author apiquere
 */

public class LoanSearchDto {

    private PageableRequest pageable;

    private LoanFilterDto filteredLoan;

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }

    public LoanFilterDto getFilteredLoan() {

        return this.filteredLoan;
    }

    public void setFilteredLoan(LoanFilterDto filteredLoan) {

        this.filteredLoan = filteredLoan;
    }
}
