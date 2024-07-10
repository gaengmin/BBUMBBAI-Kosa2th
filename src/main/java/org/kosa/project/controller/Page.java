package org.kosa.project.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Page<T> {

    private List<T> elements;
    // 현재 페이지 번호
    private int currentPage;
    // 페이지당 컨텐츠 개수
    private int pagePerSize;

    private int totalDataSize;

    public List<T> getContent() {
        return elements;
    } //현재 페이지의 데이터 목록을 반환합니다.

    public int getTotalElements() {
        return elements.size();
    } //전체 데이터 수를 반환합니다.

    public int getTotalPages() {
        return (int)Math.ceil((double)totalDataSize / pagePerSize);
    } //전체 페이지 수를 반환합니다.

    public int getNumber() {
        return currentPage;
    } //현재 페이지 번호를 반환합니다 (0부터 시작).

    public int getSize() {
        return pagePerSize;
    } //페이지 당 데이터 개수를 반환합니다.

    public boolean hasNext() {
        return getTotalPages() > currentPage;
    } //다음 페이지가 있는지 여부를 반환합니다.

    public boolean hasPrevious() {
        return currentPage > 0;
    } //이전 페이지가 있는지 여부를 반환합니다.

    public boolean isFirst() {
        return currentPage == 1;
    } //첫 번째 페이지인지 여부를 반환합니다.

    public boolean isLast() {
        return currentPage == getTotalPages();
    } //마지막 페이지인지 여부를 반환합니다.
}
