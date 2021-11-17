package warehouseapp.warehouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import warehouseapp.warehouse.entity.Attachment;
import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {

}
