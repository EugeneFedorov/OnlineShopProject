package service;

import dao.GoodsDao;
import dto.GoodsDto;
import entity.Goods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.awt.*;

/**
 * Created by laonen on 26.01.2017.
 */
public class GoodsService {
    private static volatile GoodsService instance;

    private GoodsService() {
    }

    public static GoodsService getInstance() {
        if (instance == null) {
            synchronized (GoodsService.class) {
                if (instance == null) instance = new GoodsService();
            }
        }
        return instance;
    }

    public boolean addGoods(GoodsDto dto) {
        return new GoodsDao().create(new Goods(dto.getName(),
                dto.getDescription(), dto.getPrice(), dto.getRemainingAmount())) > 0L;
    }

    public List<GoodsDto> getAllGoods() {
        List<GoodsDto> goodsDtoList = new ArrayList<>();
        for (Goods g : new GoodsDao().getAll()) {
            goodsDtoList.add(new GoodsDto(g.getId(),g.getName(),g.getDescription(),g.getPrice(),g.getRemainingAmount()));
        }
        return goodsDtoList;
    }
}