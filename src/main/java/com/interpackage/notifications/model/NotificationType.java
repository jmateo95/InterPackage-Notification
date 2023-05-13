package com.interpackage.notifications.model;

import com.interpackage.notifications.util.Constants;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name = "NotificationType")

public class NotificationType {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_notification_type", nullable = false)
    private Long idNotificationType;

    @Column (name = "notification_type_name", nullable = false, length = Constants.LENGTH_75)
    private String name;

    @Column (name = "notification_type_description", nullable = false, length = Constants.LENGTH_500)
    private String description;

    @OneToMany(mappedBy = "notificationType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();
}
