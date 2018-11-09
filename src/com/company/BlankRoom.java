package com.company;

import com.company.furnitur.Item;
import com.company.material.Material;

import java.util.*;

public class BlankRoom {
    private float wood;
    private List<Material> material = new ArrayList<>();


    public BlankRoom(List<Material> material) {
        this.material = material;
    }

    public void setWood(float wood) { this.wood = wood;}

    public HashMap<String, Integer> acceptOrder(HashMap<String, Integer> request) {
        Iterator it = request.entrySet().iterator();
        int total = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            int n = (Integer) pair.getValue();
            Material elem = material.stream().
                    filter(p -> p.getName().equals(pair.getKey())).
                    findFirst().orElse(null);
            if (elem == null) {
                System.out.println("Нет станка для изготовления - "+ pair.getKey());
                break;
            }
            total += elem.getWood() * n;
        }

        if (this.wood < total) {
            System.out.println("Не хватает Древесины! Всего - " + this.wood + "  Необходимо - " + total);
            HashMap<String,Integer> newRequest = new HashMap<>();

            for(Material x:material){//пройдёмся по всем материалам, которые может сделать цех
                String key = x.getName();
                //Если этот материал нужно сделать и хватит древесины (хотя бы на один)
                if(request.containsKey(key) && (x.getWood()<=wood) ){
                    for(int i=1; i <= request.get(key); i++)
                        if(x.getWood()<=wood){
                            wood -= x.getWood();
                            newRequest.put(key,i);
                        }
                        else
                          break;
                }
            }
            return newRequest;
        }

        System.out.println("Древесины хватает");
        return request;


    }

    public float getWood() {
        return this.wood;
    }
}
