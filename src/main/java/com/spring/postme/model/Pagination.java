package com.spring.postme.model;

import lombok.Data;

@Data
public class Pagination {
	private int pageNum;
	private int amount = 6;
	private int pageAmount = 10;
	private int startPage, endPage;
	private boolean prev, next;
	private int realEnd;
	private int total;
	
	
	public Pagination(int total, int pageAmount, int pageNum, int amount) {
        this.total = total;
        this.pageAmount = pageAmount;
        this.pageNum = pageNum;
        this.amount = amount;

        calculatePages();
    }

    // 페이지 계산 메서드
    private void calculatePages() {
        this.endPage = (int) (Math.ceil(pageNum * 1.0 / pageAmount)) * pageAmount;
        this.startPage = endPage - (pageAmount - 1);

        realEnd = (int) (Math.ceil(total * 1.0 / pageAmount));

        if (endPage > realEnd) {
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }
}
