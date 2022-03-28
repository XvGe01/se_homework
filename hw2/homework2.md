# Python��ʽ������ ���� isort
## һ�����
&emsp;&emsp;**isort**���ڰ���ĸ˳��Դ����е�`import`�������򣬲��Զ�������������ͽ��зָ���һ������ͷ�Ϊ�����������ͣ�
1. **��׼���**��*python�Դ���*��
2. **��������**��*��Ҫpip install��װ��*��
3. **���ذ�**��*�Լ�����д��*��

&emsp;&emsp;�����֮��**isort**��һ������ǿ����ʵ�õ�����ʹ`import`�б�����۵�python���߰�,���԰����Ǵ����е�`import`���ַ��ࡢ����ʵ�ָ�ʽ�����Ż�ͳһ�����񣬽�������`import`�ṹת�ɷǳ��ʺ��Ķ����Ű棬�����Ŷ�Э���ʹ���ά������Ϊ���ֱ༭���ṩ�������г���Python��Ͳ�����Կ��ٶ����е������������ҪPython3.6�����ϰ汾����Ҳ֧��Python2��
## ����ʾ��
&emsp;&emsp;��������������ʦ��������Ϊ��չʾ**isort**�Ĺ��ܣ�
- ʹ��**isort**��ʽ��ǰ��
```
from my_lib import Object

import os

from my_lib import Object3

from my_lib import Object2

import sys

from third_party import lib15, lib1, lib2, lib3, lib4, lib5, lib6, lib7, lib8, lib9, lib10, lib11, lib12, lib13, lib14

import sys

from __future__ import absolute_import

from third_party import lib3

print("Hey")
print("yo")
```
- ʹ��**isort**��ʽ����
```
from __future__ import absolute_import

import os
import sys

from third_party import (lib1, lib2, lib3, lib4, lib5, lib6, lib7, lib8,
                         lib9, lib10, lib11, lib12, lib13, lib14, lib15)

from my_lib import Object, Object2, Object3

print("Hey")
print("yo")
```
&emsp;&emsp;ͨ������ʵ�����Կ��������� **isort** ��ʽ�����`import`�������������������۹�����
## ������װ
&emsp;&emsp;��װ����ʮ�ּ򵥣�ֻ����������ʹ��```pip install isort```�����,���� python �汾(ϵͳʹ�õ��� python2 ���� python3)�Ĳ�ͬѡ��ʹ��`pip`��`pip3`��

![��װ���](install.png)

&emsp;&emsp;Ҳ���Ը�����������ָ�� **isort** �İ汾����Ӧ����Ϊ��
```
pip install isort[requirements_deprecated_finder]
```
## �ģ�isort�ڲ�ͬ�����е�ʹ��
### 4.1 ������ʹ��
- �����﷨

```
isort �ļ���.py
```
- ���н��չʾ

![1](1.png)

- ���鿴 isort �������ı����������Ӧ��

```
isort �ļ���.py --diff
```

- ���н��չʾ

![2](2.png)

### 4.2 python������ʹ��

- �����﷨
```
import isort

isort.file("�ļ���.py")
```
- ���н��չʾ

![3](3.png)

### 4.3 ��VsCode��ʹ��

- ��py�ļ�������Ҽ�

![4](4.png)

- ���н��չʾ

![5](5.png)

### 4.4 ��PyCharm��ʹ��

- �������д���ʹ��``` where isort```ȷ��isort���߰��İ�װλ��

![13](13.png)

- ��PyCharm���ⲿ���������isort
     
      ���ε��File -> Settings -> Tools -> External Tools
      
      �������ߣ�
            ���ƣ�isort
            ������˵�����֣����Ǳ����
            ����isort�İ�װλ��
            ������$FilePath$
            ����Ŀ¼��ѡ��һ������Ŀ¼����

![14](14.png)

- ���Ѿ��򿪵Ĵ���༭��������Ҽ����ҵ�External Tools -> isort��������ɡ�

![15](15.png)

- ���н��չʾ

![16](16.png)

## �壺isort����ϸʹ��

### 5.1 ���/�Ƴ� Imports

#### 5.1.1 ���ļ������һ�� import

- ��ָ���ļ������һ�� import��

```
isort -a "from __future__ import print_function" �ļ���.py
```
- ���н��չʾ

![7](7.png)

- ��������import���ļ������һ�� import��

```
isort -a "from __future__ import print_function" --append-only �ļ���.py
```
- ���н��չʾ(������import���ļ�ִ�и�����)��

![9](9.png)

- ���н��չʾ(��û��import���ļ�ִ�и�����)��

![8](8.png)

&emsp;&emsp;�ɴ˿ɼ���������ֻ��������import���ļ������ import ʱ�������á�
#### 5.1.2 ���ļ����Ƴ�һ��import

- �������÷���
```
isort --rm "os" �ļ���.py
```

- ���н��չʾ

![10](10.png)

> ������ϸϸ�ڼ�[�ٷ��ĵ�](https://link.zhihu.com/?target=https%3A//pycqa.github.io/isort/docs/configuration/add_or_remove_imports.html)

### 5.2 ������ѡ��

- isort ��������ѡ��ǳ��࣬����ͨ��```isort --help```����鿴:

![11](11.png)

- �����о�һЩ```isort --help```���صİ����ĵ��е� general options ���ֵ����ݣ�
```
general options:
  -h, --help            ��ʾ�˰�����Ϣ���˳�

  -V, --version         ��ʾ��ǰ��װ�� isort �汾

  --vn, --version-number
                        ���ؽ��е�ǰ�汾���ֶ�û�� logo �İ汾��Ϣ

  -v, --verbose         ��ʾ��ϸ��������������ļ�����ɹ�

  --only-modified, --om
                        ���ù���δ�޸��ļ�����ϸ���

  --dedup-headings      ��֪ isort ֻ��ʾһ����ͬ���Զ��� import ����ע�ͣ�
                        ��ʹ�ж������������ע����

  -q, --quiet           ��ʾ���ٵ�����������������Ϣ

  -d, --stdout          ǿ�ƽ��������� stdout ��׼����������Ǿ͵ظ���

  --overwrite-in-place  ��֪ isort ʹ����ͬ���ļ�����͵ظ�����д
                        ���׼������ȣ����ܺ��ڴ�ʹ������������½�������ȷ������
                        �ļ���־��ģʽ���ֲ���

  --show-config         �鿴 isort ��ȷ�����ã��Լ�����ѡ�����Դ

  --show-files          �鿴�ڵ�ǰѡ���£���Щ�ļ��ᱻ isort ����

  --df, --diff          ��ʾ isort ����һ���ļ����������и��ĵ� diff �������Ǿ͵ظ���

  -c, --check-only, --check
                        ����ļ����Ƿ����δ����/δ��ʽ���� import �������Ǵ�ӡ��������
                        �����޸��ļ�����û���κα仯ʱ���� 0�����ļ������¸�ʽ��ʱ���� 1

  --ws, --ignore-whitespace
                        ��ʹ���� --check-only ѡ��ʱ����֪ isort ���Կո�Ĳ���

  --sp SETTINGS_PATH, --settings-path SETTINGS_PATH, --settings-file SETTINGS_PATH, --settings SETTINGS_PATH
                        ��ʽ��������·�����ļ��������Ǹ����ļ�λ���Զ�ȷ����

  --profile PROFILE     �������õĻ��������ļ����͡������ļ�������
                        black, django, pycharm, google, open_stack,plone, attrs, 
                        hug, wemake, appnexus�� �Լ��κι���������ļ�

  --old-finders, --magic-placement
                        ʹ�������ڻ�����ʡħ�������ľɵ������õĲ������߼�

  -j [JOBS], --jobs [JOBS]
                        Ҫ���д�����ļ���

  --ac, --atomic        ������ɵ��ļ������﷨������ȷ�����ᱣ�����

  --interactive         ��֪ isort �Խ�����ʽӦ�ø���

  --format-error FORMAT_ERROR
                        �������ڴ�ӡ����ĸ�ʽ

  --format-success FORMAT_SUCCESS
                        �������ڴ�ӡ�ɹ��ĸ�ʽ

```

> ������ѡ���б��˵���ɲο�[�ٷ��ĵ�](https://link.zhihu.com/?target=https%3A//pycqa.github.io/isort/docs/configuration/options.html)

### 5.3 API

&emsp;&emsp;����ǿ��������н��棬 isort ���ṩ��һ�������� Python API ��Ϊʹ�� Python API ��import isort Ȼ���������Ҫ�ĺ�����ÿ����������������������ʾ�����ҽ��պͷ��صĶ��� Python ���ö���

- ���ڽ���ʽ��������ʹ��help(isort)�鿴��

![12](12.png)

- �����о�һЩ����API��
```
isort.code  �� ����һ������������ַ��������ڶ� import ����󷵻���
isort.check_code �� ����һ������������ַ������������ import ���Ѿ���ȷ�����򷵻� True �����򷵻� False
isort.stream �� ����һ������ Python �������������һ��������������������Ѿ��� import ����������Ĵ��롣
isort.check_stream  : Python ������������������� import ���Ѿ���ȷ�����򷵻� True �����򷵻� False
isort.file �� ���� Python Դ�ļ���·�������͵ض� import ��������
isort.check_file �� ���� Python Դ�ļ���·����������� import ���Ѿ���ȷ�����򷵻� True �����򷵻� False
isort.place_module �� ��ģ���������Ϊ�ַ���������Ϊ��ȷ���ķ���
isort.place_module_with_reason �� ��ģ���������Ϊ�ַ���������Ϊ��ȷ���ķ��࣬������ȷ���÷����ԭ��
```

> ����API�ӿ������[�ٷ��ĵ�](https://link.zhihu.com/?target=https%3A//pycqa.github.io/isort/docs/quick_start/3.-api.html)

>����APIʹ��ϸ�ڲο�[API�ο��ĵ�](https://link.zhihu.com/?target=https%3A//pycqa.github.io/isort/reference/isort/api)

## ��չ
&emsp;&emsp;����ͨ�� Python �����е�ע�Ϳ��� isort ����Ϊ��

1. ```isort: skip_file```
    - ���ã�

          �� isort ���������ļ���
    - ʵ����
      ```
      # !/bin/python3
      # isort: skip_file
      import os
      import sys
      ```
    - ��ʾ��
          
          �ô���Ӧ�þ����ܺ���ط����ļ��������档���� isort ʹ�õ�����ʽ�ܹ����������ڶ�������ע��֮ǰ���Ѿ������һЩ������һ����������ⲻ�󣬵������������ʹ�� --diff ���κν���ʽѡ��������ɻ��ҡ�
2. ```isort: skip```
   - ���ã�

          ������������ import ���ͬһ���У��� isort ������Դ� import �������򡣸������˵��������ֹ import ��䱻 isort ʶ��Ϊ import����ˣ���һ�н�����Ϊ���벢���Ƶ��ļ����벿�ֵ��·���
   - ʵ����
        ```
          import b
          import a # isort: skip  ->���е�import�������������Ȼλ��import b֮��
        ```
3. ```isort: off```
   - ���ã�
          
         �ر� isort ������# isort: off ���֮���ÿһ�ж������ֲ��䣬ֱ�� # isort: on ע�ͻ��ļ�������
   - ʵ����
      ```
      import e
      import f

      # isort: off

      import b
      import a
      ```
4. ```isort: on```
    - ���ã�
          
          ���´� isort ������������ļ������д��� isort: off ע��ʱ�������壡��������������������ĵ������Χ����δ����ĵ���顣
    
    - ʵ����
      ```
      import e
      import f

      # isort: off

      import b
      import a

      # isort: on

      import c
      import d
      ```
5. ```isort: split```
    - ����:
           
          ��֪ isort ��ǰ���򲿷�����ɣ�����δ���� import �������µ�������顣
    - ʵ����
      ```
      import e
      import f

      # isort: split

      import a
      import b
      import c
      import d
      ```
      
> ����isortʹ�÷�����[�ٷ��ĵ�](https://pycqa.github.io/isort/index.html)

