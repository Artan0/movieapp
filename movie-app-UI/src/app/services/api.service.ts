import { environment } from '../../environments/environment';

export abstract class ApiService {
  getUrl(...args: string[]): string {
    let url = environment.serverUrl;
    args.forEach((arg) => {
      url += `/${arg}`;
    });
    return url;
  }
}

export interface ApiResponse {
  success: boolean;
  data: any;
}
