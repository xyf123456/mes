/**
 * ***************************************************************************
 * Copyright (c) 2010 Qcadoo Limited
 * Project: Qcadoo Framework
 * Version: 1.2.0-SNAPSHOT
 *
 * This file is part of Qcadoo.
 *
 * Qcadoo is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation; either version 3 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 * ***************************************************************************
 */
package com.qcadoo.mes.catNumbersInDeliveries.hooks;

import static com.qcadoo.mes.catNumbersInDeliveries.contants.DeliveredProductFieldsCNID.PRODUCT_CATALOG_NUMBER;
import static com.qcadoo.mes.deliveries.constants.DeliveredProductFields.DELIVERY;
import static com.qcadoo.mes.deliveries.constants.DeliveredProductFields.PRODUCT;
import static com.qcadoo.mes.deliveries.constants.DeliveryFields.SUPPLIER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcadoo.mes.catNumbersInDeliveries.CatNumbersInDeliveriesService;
import com.qcadoo.model.api.DataDefinition;
import com.qcadoo.model.api.Entity;

@Service
public class DeliveredProductHooksCNID {

    @Autowired
    private CatNumbersInDeliveriesService catNumbersInDeliveriesService;

    public void updateDeliveredProductCatalogNumber(final DataDefinition deliveredProductDD, final Entity deliveredProduct) {
        Entity delivery = deliveredProduct.getBelongsToField(DELIVERY);
        Entity supplier = delivery.getBelongsToField(SUPPLIER);

        Entity product = deliveredProduct.getBelongsToField(PRODUCT);

        Entity productCatalogNumber = catNumbersInDeliveriesService.getProductCatalogNumber(product, supplier);

        if (productCatalogNumber != null) {
            deliveredProduct.setField(PRODUCT_CATALOG_NUMBER, productCatalogNumber);
        }
    }

}
