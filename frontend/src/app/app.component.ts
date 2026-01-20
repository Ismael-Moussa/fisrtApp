import { Component, OnInit } from '@angular/core';
import { ClientService } from './services/client.service';
import { RoleService } from './services/role.service';
import { UserService } from './services/user.service';
import { Client } from './models/client.model';
import { Role } from './models/role.model';
import { User } from './models/user.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  clients: Client[] = [];
  roles: Role[] = [];
  users: User[] = [];

  selectedClient: Client | null = null;
  selectedRole: Role | null = null;
  selectedUser: User | null = null;

  clientForm: Omit<Client, 'id'> = {
    name: '',
    email: '',
    phone: '',
    company: ''
  };

  roleForm: Omit<Role, 'id'> = {
    name: '',
    description: ''
  };

  userForm: Omit<User, 'id' | 'roleName'> = {
    fullName: '',
    email: '',
    roleId: 0
  };

  constructor(
    private clientService: ClientService,
    private roleService: RoleService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.loadClients();
    this.loadRoles();
    this.loadUsers();
  }

  loadClients(): void {
    this.clientService.getClients().subscribe((clients) => {
      this.clients = clients;
    });
  }

  loadRoles(): void {
    this.roleService.getRoles().subscribe((roles) => {
      this.roles = roles;
      if (!this.userForm.roleId && roles.length > 0) {
        this.userForm.roleId = roles[0].id;
      }
    });
  }

  loadUsers(): void {
    this.userService.getUsers().subscribe((users) => {
      this.users = users;
    });
  }

  editClient(client: Client): void {
    this.selectedClient = client;
    this.clientForm = {
      name: client.name,
      email: client.email,
      phone: client.phone,
      company: client.company
    };
  }

  resetClientForm(): void {
    this.selectedClient = null;
    this.clientForm = {
      name: '',
      email: '',
      phone: '',
      company: ''
    };
  }

  saveClient(): void {
    if (this.selectedClient) {
      this.clientService.updateClient(this.selectedClient.id, this.clientForm).subscribe(() => {
        this.loadClients();
        this.resetClientForm();
      });
      return;
    }

    this.clientService.createClient(this.clientForm).subscribe(() => {
      this.loadClients();
      this.resetClientForm();
    });
  }

  removeClient(client: Client): void {
    this.clientService.deleteClient(client.id).subscribe(() => {
      this.loadClients();
      if (this.selectedClient?.id === client.id) {
        this.resetClientForm();
      }
    });
  }

  editRole(role: Role): void {
    this.selectedRole = role;
    this.roleForm = {
      name: role.name,
      description: role.description
    };
  }

  resetRoleForm(): void {
    this.selectedRole = null;
    this.roleForm = {
      name: '',
      description: ''
    };
  }

  saveRole(): void {
    if (this.selectedRole) {
      this.roleService.updateRole(this.selectedRole.id, this.roleForm).subscribe(() => {
        this.loadRoles();
        this.resetRoleForm();
      });
      return;
    }

    this.roleService.createRole(this.roleForm).subscribe(() => {
      this.loadRoles();
      this.resetRoleForm();
    });
  }

  removeRole(role: Role): void {
    this.roleService.deleteRole(role.id).subscribe(() => {
      this.loadRoles();
      if (this.selectedRole?.id === role.id) {
        this.resetRoleForm();
      }
    });
  }

  editUser(user: User): void {
    this.selectedUser = user;
    this.userForm = {
      fullName: user.fullName,
      email: user.email,
      roleId: user.roleId
    };
  }

  resetUserForm(): void {
    this.selectedUser = null;
    this.userForm = {
      fullName: '',
      email: '',
      roleId: this.roles.length > 0 ? this.roles[0].id : 0
    };
  }

  saveUser(): void {
    if (this.selectedUser) {
      this.userService.updateUser(this.selectedUser.id, this.userForm).subscribe(() => {
        this.loadUsers();
        this.resetUserForm();
      });
      return;
    }

    this.userService.createUser(this.userForm).subscribe(() => {
      this.loadUsers();
      this.resetUserForm();
    });
  }

  removeUser(user: User): void {
    this.userService.deleteUser(user.id).subscribe(() => {
      this.loadUsers();
      if (this.selectedUser?.id === user.id) {
        this.resetUserForm();
      }
    });
  }
}
