package one.terenin.dwgrpcworkeer2.processing;

import one.terenin.Protos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataProcessor {

    public Protos.DataBundle processData(Protos.DataBundle dataBundle) {
        return dataBundle = Protos.DataBundle.newBuilder()
                .addAllSlaveCategories(dataBundle.getSlaveCategoriesList())
                .setDescription(dataBundle.getDescription() + "_mapped")
                .setName(dataBundle.getName() + "_mapped")
                .setMainCategory(dataBundle.getMainCategory() + "_mapped")
                .setProductOwner(dataBundle.getProductOwner() + "_mapped")
                .setType(dataBundle.getType() + "_mapeed")
                .build();
    }

    public Protos.DataBundleRepeated processDataRepeated(Protos.DataBundleRepeated dataBundle) {
        List<Protos.DataBundle> originalData = dataBundle.getDataList();
        List<Protos.DataBundle> list = originalData.stream().unordered().map(it -> processData(it)).toList();
        return Protos.DataBundleRepeated.newBuilder().addAllData(list).build();
    }

}
