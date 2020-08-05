package MB.Service;

import MB.Bean.Admin;

public interface AdminService {
    Admin login(String uid,String upwd);
    int remove(String uid);
}
