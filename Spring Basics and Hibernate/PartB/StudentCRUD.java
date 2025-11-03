package partb;

import org.hibernate.*;
import java.util.List;

public class StudentCRUD {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // Create
        Student s1 = new Student("Alice", "alice@example.com");
        session.save(s1);

        // Read
        List<Student> list = session.createQuery("from Student", Student.class).list();
        for (Student s : list) {
            System.out.println(s.getId() + " - " + s.getName() + " - " + s.getEmail());
        }

        // Update
        Student s2 = session.get(Student.class, s1.getId());
        s2 = new Student("Alice Updated", "alice_new@example.com");
        session.update(s2);

        // Delete
        session.delete(s2);

        tx.commit();
        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}
