# ʹ�� Github Action�Բֿ���м򵥵Ĺ���������
## ���
&emsp;&emsp;Github Actions �� Github �ṩ������Զ�������ʵ�֣������ڳ������ɺͳ��������ĳ��������߱��Զ��������಻ͬ��������������繹�������ԺͲ���ȵȡ�

## .yml �ļ���������
- ����Github doc ����� Action �Ļ����Ĺ����� yml �ļ�����������������£�����Щ�����л�������ʶ�����԰������ǿ���yml �ļ���
    - `name`�����ʾ�ù������ļ������ƣ����� Github �� actions ѡ���Ϊ������ʾ��steps ��� name �ǿ�ѡ���Ҫ������־����������ǵģ�
    - `on`���⽫�����ù��������¼����ƣ������԰����¼��б���������������� push��
    - `**jobs`��**ÿ�������������һ������ jobs ��������ֻ��һ������Ҫ�����ڱ�ʾ��ͬ��������
    - `Explore-GitHub-Actions` �����ǹ��� ID����Ҳ���Ը����Լ�����Ҫ���������� action ��ִ�й�������ʾ��
    - `runs-on`��jobs ��Ҫ������������ϣ���������ʹ���� ubuntu-latest����Ȼ��Ҳ����ʹ��windows-latest ���� macos-latest��
    - `steps`��ÿ�� jobs ���Խ���Ҫִ�е����ݻ���Ϊ��ͬ���裻
    - `run`�������ṩִ�������������ʹ����echo ��ӡ��־��
    - `uses` ��ʹ��һЩ�ٷ����ߵ������� actions ��ִ�У���������ʹ�ùٷ��� actions/checkout@v2������check-out ���ǵ� repo ��֮����������ֱ�ӷ��� repo ����ļ���

## ʵ�鲽��
- ����Github�ֿⴴ��Ŀ¼`.github/workflows/`��Ȼ���� `workflows` �ļ����ﴴ����ͬ�� .yml �ļ�������Ӧ����ִ�в�ͬ���¼������� `git push` ��`pull request` ��, ����ʹ����ʦ�ṩ��ʾ���ļ�[https://github.com/ChenZhongPu/swufe-se/blob/main/.github/workflows/demo.yml](https://github.com/ChenZhongPu/swufe-se/blob/main/.github/workflows/demo.yml)��

![demo_yml.png](demo_yml.png)

    - ����Ҳ����ͨ������ֿ��е�Actionѡ����д�����

![action.png](action.png)   

- ����demo.yml�ļ��а�����python������������������ڴ���`.github/workflows/demo.yml`Ŀ¼�ļ�֮ǰ�ڲֿ��д����漰����`main.py`��`requirements`��`test_main.py`������ļ���

![pythonCode.png](pythonCode.png)

- ������Ƕ�`test_main.py`�ļ������޸ģ������Ϊ��ȷ�Ĳ��Դ���
![test_main.png](test_main.png)

- �� GitHub �ֿ�������Ӧ�� .github/workflows/demo.yml �ļ�֮���Ժ�ÿ�� push �����Դ��� action ���Զ�ִ�У��Դ�����ɿɳ������Զ����ɺ͹���������

![success.png](success.png)

    - ���������ʧ��ʱ���û������յ���������ʼ���

![failed.png](failed.png)

    - ���������ӣ����� github �ϲ鿴����ִ�н����

![failed1.png](failed1.png)

![failed_detail.png](failed_detail.png)






