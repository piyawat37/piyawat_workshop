package com.example.evitected.workshop.datamodel;

/**
 * Created by Evitected on 12/11/2559.
 */
public class ResponseNews {
    Result result;

    News news;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
