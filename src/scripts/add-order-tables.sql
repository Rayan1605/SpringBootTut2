CREATE TABLE 'beer_order' (
id varchar(36) NOT NULL,
customer_date date(6) DEFAULT NULL,
 customer_ref varchar(255) DEFAULT NULL,
 last_modified_date DATETIME(6) DEFAULT NULL,
version BIGINT DEFAULT NULL,
    customer_id varchar(36) DEFAULT NULL,
    PRIMARY KEY (id)
    CONSTRAINT FOREIGN KEY (customer_id) REFERENCES customer(id)
) ENGINE=InnoDB;

CREATE TABLE 'beer_order_line' (
    id varchar(36) NOT NULL,
    beer_id  varchar(36) DEFAULT NULL,
    created_date DATETIME(6) DEFAULT NULL,
    last_modified_date DATETIME(6) DEFAULT NULL,
    order_quantity INT DEFAULT NULL,
    quantity_allocated INT DEFAULT NULL,
    version BIGINT DEFAULT NULL,
    beer_order_id varchar(36) DEFAULT NULL,
    PRIMARY KEY (id)
    CONSTRAINT FOREIGN KEY (beer_order_id) REFERENCES beer_order(id),
    CONSTRAINT FOREIGN KEY (beer_id) REFERENCE beer (id)
    ) ENGINE=InnoDB;