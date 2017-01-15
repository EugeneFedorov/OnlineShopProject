package dao;

/**
 * Created by laonen on 15.01.2017.
 */
class SqlBuilder {

    private String select;
    private String from;
    private String join;
    private String on;
    private String equal;
    private String where;
    private String and;
    private String insert;

    SqlBuilder select(String select) {
        this.select = " select " + select;
        return this;
    }

    SqlBuilder from(String from) {
        this.from = " from " + from;
        return this;
    }

    SqlBuilder join(String join) {
        this.join = " join " + join;
        return this;
    }

    SqlBuilder on(String on) {
        this.on = " on " + on;
        return this;
    }

    SqlBuilder equal(String equal) {
        this.equal = " = " + equal;
        return this;
    }

    SqlBuilder where(String where) {
        this.where = " where " + where;
        return this;
    }

    SqlBuilder and(String and) {
        this.and = " and " + and;
        return this;
    }

    SqlBuilder insert(String insert) {
        this.insert = " insert into " + insert;
        return this;
    }

    String build() {
        String result = "";
        result += select != null ? select : "";
        result += from != null ? from : "";
        result += join != null ? join : "";
        result += on != null ? on : "";
        result += equal != null ? equal : "";
        result += where != null ? where : "";
        result += and != null ? and : "";
        return result;
    }
}
