package com.zzzwww;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import com.zzzwww.post.dao.AttachmentAcquisitionMapper;
import com.zzzwww.post.dao.PropertyInfoTempMapper;
import com.zzzwww.post.dto.entity.AttachmentAcquisition;
import com.zzzwww.post.dto.entity.PostLog;
import com.zzzwww.post.dto.entity.PropertyInfoTemp;
import com.zzzwww.post.dto.entity.User;
import com.zzzwww.post.service.AttachmentService;
import com.zzzwww.post.service.TestThreadLocalServicce;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootTest
class BootTestApplicationTests {

    @Autowired
    TestThreadLocalServicce testThreadLocalServicce;
    @Autowired
    AttachmentAcquisitionMapper attachmentAcquisitionMapper;
    @Autowired
    AttachmentService attachmentService;

    @Test
    void contextLoads() {
        List<String> strings = Arrays.asList("a", "b", "c", "d");
        String a = strings.stream().reduce("", String::concat);
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Integer sum = integers.stream().reduce(1, Integer::sum);
        System.out.println(sum);
    }


    @Test
    void testLocalVariable() {
        testThreadLocalServicce.test1();
    }


    @Test
    @Rollback(false)
    void batchDData() {

        for (long i = 0; i < 800; i++) {
            List<AttachmentAcquisition> acquisitions = new ArrayList<>(1000000);
            for (long j = 0; j < 5000; j++) {
                AttachmentAcquisition attachmentAcquisition = new AttachmentAcquisition();
                attachmentAcquisition.setTargetId(j);
                attachmentAcquisition.setTargetType((int) i);
                attachmentAcquisition.setFileName("");
                attachmentAcquisition.setFileKey("");
                attachmentAcquisition.setLevelId(j);
                attachmentAcquisition.setCreateBy(0L);
                attachmentAcquisition.setCreateTime(new Date());
                attachmentAcquisition.setUpdateBy(0L);
                attachmentAcquisition.setUpdateTime(new Date());
                attachmentAcquisition.setDeleted(0);
                acquisitions.add(attachmentAcquisition);
            }
            attachmentService.saveBatch(acquisitions);
        }
    }

    public static Integer num = 1;

    @Test
    void testBuildParam() {
        List<String> param1 = new ArrayList<>();
        param1.add("11");
        param1.add("22");
        param1.add("33");
        Map<String, String> param2 = new HashMap<>();
        param2.put("11", "bar1");
        param2.put("33", "bar2");
        buildParam(this::buildSting, param1, param2);
        createUser();
    }


    @Test
    void test1() {
        Class<PostLog> postLogClass = PostLog.class;
        Annotation[] annotations = postLogClass.getAnnotations();
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> type = annotation.annotationType();
            System.out.println(type);
        }

    }


    public static void main(String[] args) {

        BigDecimal amount1 = new BigDecimal(0.02);
        BigDecimal amount2 = new BigDecimal(0.03);
        BigDecimal amount3 = amount2.subtract(amount1);
        System.out.println(amount3);  // 0.0099999999999999984734433411404097569175064563751220703125

    }

    private void buildSting(String message) {
        System.out.println("第" + num + "次执行！message :" + message);
        num++;
    }

    private void createUser() {
        User user = new User();  // 使用new关键字
        Class<User> userClass = User.class; // 类的class属性
        Constructor<?>[] constructors = userClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
        }
        Field[] declaredFields = userClass.getDeclaredFields();
        Method[] methods = userClass.getMethods();
        try {
            userClass.getMethod("xxx", Void.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }




    private <T> void buildParam(Consumer<T> consumer, List<T> list, Map<T, T> param) {
        Assert.notNull(param, "Param is null.");
        Assert.notNull(list, "Param is null.");
        for (Map.Entry<T, T> entry : param.entrySet()) {
            if (list.contains(entry.getKey())) {
                consumer.accept(entry.getValue());
            }
        }
    }

    @Test
    public void test5() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Callable<Integer> a1 = ()->{
            System.out.println("222");
            Thread.sleep(5000);
            return 1;
        };

        Callable<Integer> a2 = ()->{
            System.out.println("111");
            return 2;
        };
        Future<Integer> future1 = executorService.submit(a1);
        future1.cancel(Boolean.TRUE);
        System.out.println("任务是否被打断：" + future1.isCancelled());
//        List<Future<Integer>> futures = executorService.invokeAll(Arrays.asList(a1, a2));
//        System.out.println(futures);
//        for (Future<Integer> future : futures) {
//            future.cancel(Boolean.TRUE);
//            System.out.println("任务是否被打断：" + future.isCancelled());
//            System.out.println(future.get());
//        }
    }


    @Autowired
    private PropertyInfoTempMapper propertyInfoTempMapper;

    static String json = "[\"160677255474600000010000048239\",\"cc3180b666f84ce8b04a2468b66afe17\",\"159917428641300000010000048239\",\"162504724545300000010000024187\",\"155177188807400000010000000245\",\"156089996680400000010000048239\",\"156442906093600000010000048104\",\"155617840620900000010000000039\",\"cb129765a21d4affaefbe86a603aaddb\",\"4c56b335d6164bf3b091c1e9b0ec307f\",\"156433956327200000010000048104\",\"1bec5c8407464ae2b688fb43cc2e580c\",\"161305552982000000010000000039\",\"156447053933000000010000048104\",\"9024f237678b499dbd8e6d0c7375a26a\",\"162570972564600000010000002206\",\"154771695890200000010000000039\",\"156463995887100000010000048104\",\"162499340690300000010000001047\",\"a401f9e249d24edb871d522b3ae3e851\",\"159897687980300000010000048239\",\"1b713e01f54e4c7885cfab9ecdbc01ca\",\"154771183575600000010000048239\",\"162406444992500000010000024187\",\"162638045598100000010000048239\",\"162378370216500000010000024064\",\"161834589636600000010000048104\",\"c93ff623d52247ac8cf3c527b8c965ea\",\"162611747636600000010000000039\",\"5667cd9d0c9540f984cb9930c66c4f36\",\"154789110203100000010000048239\",\"155356819671000000010000000039\",\"156493668667000000010000048104\",\"159781596967000000172016008244\",\"2af0c599152b4402bea1d2c326abcf73\",\"154771718570400000010000000039\",\"156493818199200000010000048104\",\"161834589291200000010000048104\",\"f91bf0b09e1f41a1831e92506149eadb\",\"156471094022700000010000048104\",\"e76a60c6bfbe4d56aa49ab71afd10190\",\"055f6710977f45d4bf080fd9b6884123\",\"e889d16460704e13882e446849305e13\",\"a622fafe2371445abb6085e649e0abef\",\"156418017576200000010000048104\",\"ce05ed9b30f440fbada85d16fab7b844\",\"b21d5e10a5cf4d528bc14a6e59149ef9\",\"162628566397700000010000002206\",\"161984164989000000010000002206\",\"161682485107400000010000048104\",\"66d52724f14441c5837e896dced0212a\",\"159983317670900000010000000039\",\"155176206338200000010000000245\",\"bd578e19a4324a27b828ce5890446eaa\",\"155175900673200000010000000245\",\"162696964408800000010000000039\",\"159983392371900000010000000039\",\"162514456546800000010000024187\",\"156425027962200000010000048104\",\"154786431436500000010000048239\",\"4fdf693441904f4db7ee8c41fd735ef2\",\"155177077215600000010000000245\",\"19c005b2400b4fb2aa367e81a820b747\",\"155177240310800000010000000245\",\"160645697218200000010000002206\",\"156455050180300000010000048104\",\"160036268337400000010000048239\",\"159963461830100000010000048239\",\"cf4c516f7c884be58882cd5def916982\",\"993e2e8539f14957b9f78378871e03ee\",\"156478648883900000010000048104\",\"338255275a374ba19d0a8d25ccc5cbfc\",\"156474466706800000010000048104\",\"156483966874900000010000048104\",\"159781596968600000172016008244\",\"154771724027100000010000000039\",\"155598983153100000010000000039\",\"156492340757200000010000048104\",\"156442650682200000010000048104\",\"159971119490800000010000048239\",\"545dcd1cd3474b0998c307c31e01fc26\",\"162437773232400000010000001047\",\"bd4ef760fd72457c9b00f676e26e319c\",\"160061523080600000010000000039\",\"156478604391900000010000048104\",\"6d8d6fe126444918a59f71c53d1659af\",\"162561274673200000010000048104\",\"d323868e8abd4ef795e7ce6d19019a41\",\"162697329457000000010000048104\",\"be35359caa1f447c808539d8b565c522\",\"162333722744400000010000001070\",\"162560076393300000010000000039\",\"2e067b2ea93345fa97d5ff6eece9a502\",\"159361837329000000010000000039\",\"162208805119800000010000001251\",\"9831250a42504525be51982dc4235eaf\",\"3712d14e83254925bd342c532a72ab38\",\"154771174372800000010000048239\",\"157592265750800000010000000039\",\"162197288283400000010000001251\",\"155175721247500000010000000245\",\"162086773203200000010000002206\",\"cc64364156db441584108bc23d097400\",\"156434236692400000010000048104\",\"156425168221700000010000048104\",\"160036088036200000010000000039\",\"154771235205200000010000048239\",\"162406441574500000010000024187\"]\n";

    @Test
    public void test232() {
        List<String> strings = new ArrayList<>();
        ExcelReader reader = ExcelUtil.getReader("C:\\Users\\chaojie.wen\\Documents\\sp58\\sum.xlsx");
        List<Entity> entities = reader.readAll(Entity.class);
        System.out.println(entities.size());
        List<String> notIds = JSON.parseArray(json, String.class);
        List<PropertyInfoTemp> propertyInfoTemps = propertyInfoTempMapper.selectByIds(notIds);
        List<String> ids = new ArrayList<>();
        for (PropertyInfoTemp propertyInfoTemp : propertyInfoTemps) {
            String pathNum = propertyInfoTemp.getSiteFullAddress().split(" ")[0];
            String zipCode = propertyInfoTemp.getSiteZip();
            BigDecimal bedRoomNumber = propertyInfoTemp.getBedRoomNumber();
            String bedRoom = String.valueOf(bedRoomNumber.intValue());
//            BigDecimal bathRoomNumber = propertyInfoTemp.getBathRoomNumber();
            String yearBuilt = propertyInfoTemp.getYearBuilt();
            for (Entity entity : entities) {
                String prefix = entity.getAddress().split(" ")[0];
                if (Objects.equals(prefix, pathNum)
                        && Objects.equals(entity.getZipCode(), zipCode)
//                        && Objects.equals(entity.getBathrooms(), bathRoomNumber)
                        && Objects.equals(entity.getBedrooms(), bedRoom)
                        && Objects.equals(entity.getBuiltYear(), yearBuilt)
                ) {
                    ids.add(propertyInfoTemp.getId());
                    break;
                }
            }
        }
        System.out.println(ids.size());
        System.out.println(ids);
        System.out.println(CollectionUtil.subtract(notIds, ids));
    }

    @Data
    class Entity {
        private String address;
        private String zipCode;
        private String bedrooms;
        private String bathrooms;
        private String builtYear;
        private String dataSource;
        private String sourceId;
        private String street;
        private String cityName;
        private String stateCode;
        private String deleted;

    }
}
