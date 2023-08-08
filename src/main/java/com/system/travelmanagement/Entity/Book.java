package com.system.travelmanagement.Entity;

import jakarta.persistence.*;
import lombok.*;

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name="book")
    public class Book {
        @Id
        @SequenceGenerator(name = "hrs_room_seq_gen", sequenceName = "pms_room_id_seq", allocationSize = 1)
        @GeneratedValue(generator = "hrs_room_seq_gen", strategy = GenerationType.SEQUENCE)
        private Integer id;

        @Column
        private String checkin;

        @Column
        private String checkout;

        @Column
        private String People;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "dest_id",
                referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "FK_destId"))
        private Destination destId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id",
                referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "FK_userId"))
        private User userId;
    }

