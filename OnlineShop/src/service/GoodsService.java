package service;

import dao.GoodsDao;
import dto.GoodsDto;
import entity.Goods;

/**
 * Created by laonen on 26.01.2017.
 */
public class GoodsService {
    private static final GoodsService instance = new GoodsService();

    private GoodsService() {
    }

    public static GoodsService getInstance() {
        return instance;
    }

    public boolean addGoods(GoodsDto dto) {
        return new GoodsDao().create(new Goods(dto.getName(),
                dto.getDescription(), dto.getPrice(), dto.getRemainingAmount())) > 0L;
    }
}
